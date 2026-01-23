package com.aml.sentinel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SanctionsDataLoader implements CommandLineRunner {

    private final VectorStore vectorStore;

    @Override
    public void run(String... args) {
        var sanctions = List.of(
                new Document("""
                ID: ENT-9901
                Name: Global Trade & Logistics Ltd
                Location: Tehran, Iran
                Reason: Proliferation of dual-use technologies.
                """, Map.of("type", "entity", "risk", "high")),

                new Document("""
                ID: IND-4422
                Name: Ivan Dragovic
                Location: Moscow, Russia
                Reason: Associated with sanctioned financial institutions.
                """, Map.of("type", "individual", "risk", "critical")),

                new Document("""
                ID: ENT-8812
                Name: Cuba Libre Exporting
                Location: Havana, Cuba
                Reason: State-sponsored maritime trade restrictions.
                """, Map.of("type", "entity", "risk", "medium"))
        );

        vectorStore.add(sanctions);
        System.out.println(">>> Sanctions Test Data Ingested into PGVector.");
    }
}