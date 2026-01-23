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
        List<Document> sanctionsData = List.of(
                // TS-001: Direct Match
                new Document(
                        "Elena Petrova, based in Saint Petersburg, Russia. Associated with defense sector consulting and high-risk financial transfers.",
                        Map.of("country", "Russia", "risk_level", "High", "entity_type", "Individual")
                ),

                // TS-002: Geo False Positive context
                // We add a generic "Cuba" sanction to see if the AI can distinguish it from "Havana Ave, Miami"
                new Document(
                        "Trade embargo active for all entities operating within the Republic of Cuba. Restrictions apply to Caribbean-based operations.",
                        Map.of("country", "Cuba", "risk_level", "High", "entity_type", "Jurisdiction")
                ),

                // TS-003: Industry Pivot / Entity Alias
                new Document(
                        "Z-Tech Solutions (North Korea/Pyongyang). Front company for illicit software procurement and cloud infrastructure financing.",
                        Map.of("country", "North Korea", "risk_level", "Critical", "entity_type", "Organization")
                ),

                // TS-004: Baseline (No specific match needed, but we add generic "London" data to ensure precision)
                new Document(
                        "Standard retail banking profile: John Smith, London, United Kingdom. No active sanctions found for this common name in this region.",
                        Map.of("country", "UK", "risk_level", "Low", "entity_type", "Individual")
                )
        );

        System.out.println(">>> Initializing Vector Store with Sanctions Test Data...");
        vectorStore.add(sanctionsData);
        System.out.println(">>> Data Load Complete. Sentinel is now armed.");
    }
}