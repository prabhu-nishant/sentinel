package com.aml.sentinel.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SanctionsService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public record SanctionsDecision(String status, String reasoning, double confidence) {}

    public SanctionsService(ChatClient.Builder builder, VectorStore vectorStore) {
        this.vectorStore = vectorStore;
        this.chatClient = builder
                .defaultSystem("You are a Senior Sanctions Investigator at TD Securities.")
                .build();
    }

    public SanctionsDecision screenTransaction(String paymentDetails, String flaggedName) {
        var searchResults = vectorStore.similaritySearch(SearchRequest.builder()
                        .query(flaggedName)
                        .topK(2)
                        .build()
        );

        var sanctionsContext = searchResults.stream()
                .map(doc -> doc.getFormattedContent())
                .collect(Collectors.joining("\n---\n"));

        return chatClient.prompt()
                .user(u -> u.text("""
                        Analyze this potential match.
                        Payment: {payment}
                        Sanctions Found: {context}
                        
                        Distinguish between geographical coincidences and true threats.
                        """)
                        .param("payment", paymentDetails)
                        .param("context", sanctionsContext))
                .call()
                .entity(SanctionsDecision.class);
    }
}