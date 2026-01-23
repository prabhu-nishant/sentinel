
---

# 🛡️ AML Sentinel: Intelligent Reasoning Layer

The **AML Sentinel** project transforms the "Compliance Bottleneck" into a high-speed, automated advantage. In institutional banking, the primary pain point isn't identifying potential risks—it's the massive volume of **False Positives** that slow down legitimate global commerce.

## 🎯 The Primary Purpose

The Sentinel acts as an **Intelligent Reasoning Layer** that sits between traditional "Fuzzy Matching" engines and human compliance officers. Its goal is to analyze the **semantic context** of an AML/Sanctions alert to determine if it is a genuine threat or a benign coincidence (e.g., a street name matching a country name).

## 🚀 Strategic Impact

### 1. Operational Efficiency

* **Reduced False Positives:** By auto-dismissing "obvious" false hits (like our "Cuba Ave" example), the bank can reduce the manual workload of Level 1 investigators by **60–80%**.
* **STP Optimization:** Fewer manual holds mean a higher **Straight-Through Processing (STP)** rate, ensuring corporate clients get their funds faster.

### 2. Regulatory Compliance & Auditability

* **Explainable AI (XAI):** Unlike older AI models that give a "Yes/No" score, the SLM (Phi-3) provides a **written justification**. This creates a clear audit trail for regulators to see *why* a specific decision was made.
* **Consistency:** The AI applies the same logic to every transaction, eliminating human fatigue and subjectivity in the screening process.

### 3. Data Sovereignty & Security

* **Zero PII Leakage:** Because the sentinel runs **locally** (using Ollama/Phi-3), sensitive customer data never leaves the bank's secure perimeter. This bypasses the massive legal and security hurdles of using public cloud LLMs for sensitive financial data.

---

## 🏗️ How it Impacts the Architecture

By implementing this, you move from a **Deterministic** architecture (if/then/else) to a **Probabilistic** one (Reasoning/Context/Scoring).

| Feature | Legacy System Impact | AML Sentinel Impact |
| --- | --- | --- |
| **Logic** | Keyword-based (Fragile) | **Context-based (Robust)** |
| **Speed** | Instant, but high manual follow-up | **Near-instant + automated follow-up** |
| **Accuracy** | High sensitivity, low precision | **High sensitivity + High precision** |
| **Cost** | High (Human intensive) | **Low (Compute intensive)** |

---

## 🛠️ Infrastructure Setup

Follow these steps to spin up the AI and Database backbone using **Docker Compose**.

### 1. Prerequisites

* **Docker Desktop** installed and running.
* **Java 21+** and **Spring Boot 3.5.10**.
* **Memory:** Ensure Docker has at least **8GB of RAM** allocated for the LLM.

### 2. Start the Containers

Navigate to your project root (where your `docker-compose.yml` is located) and run:

```bash
docker compose up -d

```

### 3. Initialize the AI & Database

Run these commands to verify the "brain" (Ollama) and "memory" (Postgres) are ready:

```bash
# Download the Phi-3 model
docker exec -it aml-ollama ollama pull phi3

# Enable the Vector extension in Postgres
docker exec -it aml-postgres psql -U admin -d sanctions_db -c "CREATE EXTENSION IF NOT EXISTS vector;"

```

---

## 🧪 Test Scenarios & Validation Data

| Scenario ID | Test Case Name | Input Flagged Name | Transaction Context | Expected Disposition |
| --- | --- | --- | --- | --- |
| **TS-001** | **Direct Match** | Elena Petrova | Transfer of $50k to Elena Petrova in St. Petersburg for "Consulting". | **ESCALATE** |
| **TS-002** | **Geo False Positive** | Blue Horizon | Invoice for "Blue Horizon Cafe" located at 555 Havana Ave, Miami, FL. | **CLEARED** |
| **TS-003** | **Industry Pivot** | Z-Tech Solutions | Payment for "Z-Tech Cloud" software. Beneficiary in Pyongyang. | **ESCALATE** |
| **TS-004** | **Common Name** | John Smith | Monthly payroll for John Smith, London, UK. | **CLEARED** |

---
