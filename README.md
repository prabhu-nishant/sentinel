---

# 🛡️ AML Sentinel: Intelligent Reasoning Layer

The **AML Sentinel** project transforms the Compliance Bottleneck into a high speed, automated advantage. In institutional banking, the primary pain point isn't identifying potential risks; it's the massive volume of **False Positives** that slow down legitimate global commerce.

## 🎯 The Primary Purpose

The Sentinel acts as an **Intelligent Reasoning Layer** that sits between traditional Fuzzy Matching engines and human compliance officers. Its goal is to analyze the **semantic context** of an AML or Sanctions alert to determine if it is a genuine threat or a benign coincidence. For example, it can distinguish between a street name and a country name.

## 🚀 Strategic Impact

### 1. Operational Efficiency

* **Reduced False Positives:** By auto dismissing obvious false hits like our Cuba Avenue example, the bank can reduce the manual workload of Level 1 investigators by **60 to 80 percent**.
* **STP Optimization:** Fewer manual holds mean a higher **Straight Through Processing** rate, ensuring corporate clients get their funds faster.

### 2. Regulatory Compliance and Auditability

* **Explainable AI:** Unlike older AI models that give a simple score, the Small Language Model provides a **written justification**. This creates a clear audit trail for regulators to see exactly why a specific decision was made.
* **Consistency:** The AI applies the same logic to every transaction, eliminating human fatigue and subjectivity in the screening process.

### 3. Data Sovereignty and Security

* **Zero PII Leakage:** Because the sentinel runs **locally** using Ollama and Phi 3, sensitive customer data never leaves the bank's secure perimeter. This bypasses the massive legal and security hurdles of using public cloud AI for sensitive financial data.

---

## 🏗️ How it Impacts the Architecture

By implementing this, you move from a **Deterministic** architecture based on simple logic to a **Probabilistic** one focused on reasoning and context.

| Feature | Legacy System Impact | AML Sentinel Impact |
| --- | --- | --- |
| **Logic** | Keyword based | **Context based** |
| **Speed** | Instant but high manual follow up | **Near instant with automated follow up** |
| **Accuracy** | High sensitivity but low precision | **High sensitivity and High precision** |
| **Cost** | High and human intensive | **Low and compute intensive** |

---

## 🛠️ Infrastructure Setup

Follow these steps to spin up the AI and Database backbone using **Docker Compose**.

### 1. Prerequisites

* **Docker Desktop** installed and running.
* **Java 21** and **Spring Boot 3.5.10**.
* **Memory:** Ensure Docker has at least **8GB of RAM** allocated for the model.

### 2. Start the Containers

Navigate to your project root and run:

```bash
docker compose up -d

```

### 3. Initialize the AI and Database

Run these commands to verify the brain and memory are ready:

```bash
# Download the Phi 3 model
docker exec -it aml-ollama ollama pull phi3

# Enable the Vector extension in Postgres
docker exec -it aml-postgres psql -U admin -d sanctions_db -c "CREATE EXTENSION IF NOT EXISTS vector;"

```

---

## 🧪 Test Scenarios and Validation Data

| Scenario ID | Test Case Name | Input Flagged Name | Transaction Context | Expected Disposition |
| --- | --- | --- | --- | --- |
| **TS001** | **Direct Match** | Elena Petrova | Transfer of 50k to Elena Petrova in St. Petersburg for Consulting. | **ESCALATE** |
| **TS002** | **Geo False Positive** | Blue Horizon | Invoice for Blue Horizon Cafe located at 555 Havana Ave, Miami, FL. | **CLEARED** |
| **TS003** | **Industry Pivot** | Z Tech Solutions | Payment for Z Tech Cloud software. Beneficiary in Pyongyang. | **ESCALATE** |
| **TS004** | **Common Name** | John Smith | Monthly payroll for John Smith, London, UK. | **CLEARED** |

---
