You’re thinking in the right direction: **SCN-04 and SCN-05 don’t both need to exist as full standalone scenarios** if your goal is a clean, semantic scenario set. In practice:

* **`sel.distinct(TRUE)` is an implementation detail**, so it’s better expressed as an **expected behavior constraint** (e.g., “at most one logical match/update per incoming row”) inside the “normal” matching scenarios (SCN-01 / SCN-03), rather than as a separate “distinct” scenario.
* **Ambiguous multi-security matches (SCN-04)** is *not* just about `distinct`. It’s about **data integrity and deterministic behavior**. If your system can ever match one incoming row to multiple securities, you need one explicit scenario somewhere to define the outcome (reject / pick one / publish error). If you’re confident that upstream/master data guarantees uniqueness, you can downgrade it to an **assumption + guardrail** instead of a full scenario.

Below is a suggested rewrite: **remove SCN-05**, and **fold the “distinct” expectation into SCN-01 and SCN-03**. For SCN-04, either keep it as a short “negative” scenario or convert it into an “Assumption + Validation” section.

---

## Option A (Recommended): Keep SCN-04 (short), remove SCN-05, fold `distinct` into SCN-01/03

### SCN-01 (HK): Identify a security by any supported identifier (unique logical match)

**Given**

* The loader runs in HK mode.
* Security lookup supports ISIN / ECB_CODE / QUICKCODE across multiple incoming identifier fields.
* The Security Master contains a matching security.

**When**

* An incoming HK price row arrives with at least one supported identifier matching the security.

**Then**

* The system must resolve the row to the correct security.
* The system must produce **at most one logical match** for that incoming row (no duplicate matches caused by multiple identifiers).
* The row proceeds to downstream processing exactly once.

---

### SCN-03 (HK + RIC enabled): RIC filtering restricts security selection (unique logical match)

**Given**

* The loader runs in HK mode.
* RIC lookup/filtering is enabled and configured.
* The incoming row may match multiple securities by identifier, but only one satisfies the RIC rule.

**When**

* The loader performs security matching.

**Then**

* The system must select only the security satisfying the RIC rule.
* The system must produce **exactly one logical match** for that incoming row (no duplicates).
* The row proceeds to downstream processing exactly once.

---

### SCN-04 (HK conflict): One incoming row matches multiple securities (deterministic outcome)

**Given**

* The loader runs in HK mode.
* Due to data quality issues, the same identifier can match multiple securities.

**When**

* The loader performs security matching for that incoming row.

**Then**

* The system must follow a **deterministic conflict strategy**:

  * Either reject the row and record an “ambiguous match” error, **or**
  * apply a documented priority rule to select exactly one security.
* The chosen behavior must be consistent across re-runs.

---

## Option B (If you want an even leaner scenario set): Remove SCN-04 too, but add an explicit assumption/guardrail

### Assumption: Security matching is unique in production data

* For any incoming row, the configured identifier set resolves to **at most one** security.
* If multiple securities match, the system must fail fast and record an error (implementation guardrail).

(You’d still validate this via a UT/AT, but you don’t present it as a “business scenario”.)

---

## Why folding `distinct` into SCN-01/03 is better

* It keeps scenarios **semantic** (“one incoming row results in one logical match / one processing”), rather than describing SQL mechanics.
* It preserves test intent: your Java migration still must ensure **no duplicate processing**, regardless of how the join is implemented.

If you want, I can rewrite the full `.feature` file accordingly (SCN-01/02/03 updated with the “unique logical match” expectation, SCN-05 removed, SCN-04 kept short).
