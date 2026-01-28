非常好的提醒，你这个意识本身就已经是**高级迁移工程师视角**了 👏
下面是 **完全语义化 + 业务行为导向的英文版本 SCN-01 ~ SCN-05**（字段与 SQL 细节只作为 *data examples*，不再抢语义主线）。

你可以把这段直接作为 **Output 1: TreatsPricerLoader Scenarios** 的正文。

---

# TreatsPricerLoader – Join & Matching Scenarios (Semantic Version)

## SCN-01 (HK): A price row can identify a security by any supported identifier

**Intent:**
When running in Hong Kong mode, the system should be able to identify a security as long as *any one* of the supported identifiers matches.

### Given

* The loader is running in **HK mode** (`m_priceSourceId = "PRICEFILE"`).
* The system is configured to use multiple identifiers for security lookup (ISIN, ECB_CODE, QUICKCODE).
* A security exists in the Security Master.

### When

* A price feed row arrives that contains **at least one** identifier matching the security.

### Then

* The system must successfully resolve the price row to the corresponding security.
* The row must proceed to price/security update logic.

**Data example**

| Security Master | ISIN  | ECB_CODE | QUICKCODE | sec_sid |
| --------------- | ----- | -------- | --------- | ------- |
| row1            | XS111 | ECB01    | QC01      | 1001    |

| Incoming HK Price Row | SEC_ID_CODE1 | SEC_ID_CODE2 | SEC_ID_CODE3 |
| --------------------- | ------------ | ------------ | ------------ |
| rowA                  | XS111        | NULL         | NULL         |

---

## SCN-02 (London): A price row must identify a security by ISIN only

**Intent:**
When running in London mode, only ISIN is considered a valid identifier for security matching.

### Given

* The loader is running in **London mode** (`m_priceSourceId != "PRICEFILE"`).
* A security exists with ISIN = XS222.

### When

* A price feed row arrives with `SECURITY_ID_ISIN = XS222`.

### Then

* The system must match the row to the security with ISIN = XS222.
* Other identifiers (ECB_CODE, QUICKCODE, RIC) must not be used for matching.

**Data example**

| Security Master | ISIN  | sec_sid |
| --------------- | ----- | ------- |
| row1            | XS222 | 2001    |

| Incoming London Price Row | SECURITY_ID_ISIN |
| ------------------------- | ---------------- |
| rowB                      | XS222            |

---

## SCN-03 (HK + RIC enabled): RIC filtering must restrict security selection

**Intent:**
When RIC-based filtering is enabled, the system must not assign prices to a security whose RIC does not meet the configured rules—even if other identifiers match.

### Given

* The loader is running in HK mode.
* RIC lookup/filtering is enabled (`m_bLookupBondByRIC = true`).
* RIC filtering rules are configured.

### When

* A price row matches multiple securities by identifier,
* but only one of them satisfies the RIC rule.

### Then

* The system must select **only** the security that satisfies the RIC filter.
* Other potential matches must be ignored.

**Data example**

| Security Master | ISIN  | RIC     | sec_sid |
| --------------- | ----- | ------- | ------- |
| row1            | XS333 | RIC_OK  | 3001    |
| row2            | XS333 | RIC_BAD | 3002    |

| Incoming HK Price Row | SEC_ID_CODE1 | RIC    |
| --------------------- | ------------ | ------ |
| rowC                  | XS333        | RIC_OK |

---

## SCN-04 (HK conflict): A price row matches multiple securities

**Intent:**
The system must behave deterministically when a single price row can be linked to more than one security.

### Given

* The loader is running in HK mode.
* Due to data quality issues, the same identifier is associated with multiple securities.

### When

* A price row matches more than one security.

### Then

The system must follow a **defined conflict strategy** (to be confirmed):

* Either reject the row and record an error,
* Or apply a documented priority rule to choose exactly one security.

**Open question:**
What is the expected behavior when multiple sec_sid values are matched?

---

## SCN-05 (HK duplicates): Duplicate matches must not produce duplicate updates

**Intent:**
When a price row matches the same security through multiple identifiers, the system must not process it more than once.

### Given

* A single price row matches the same security via more than one identifier.

### When

* The join produces duplicate logical matches.

### Then

* The system must treat them as **one** logical update:

  * Only one security update,
  * Only one price update,
  * Only one history record,
  * Only one publish event.

**Data example**

| Security Master | ISIN  | ECB_CODE | QUICKCODE | sec_sid |
| --------------- | ----- | -------- | --------- | ------- |
| row1            | XS555 | ECB55    | QC55      | 5001    |

| Incoming HK Price Row | SEC_ID_CODE1 | SEC_ID_CODE2 |
| --------------------- | ------------ | ------------ |
| rowE                  | XS555        | ECB55        |

---

如果你愿意，下一步我可以帮你把这 5 个场景：

* 映射成 **Java service 层方法责任图**
* 或转成 **Cucumber feature (.feature) 文件示例**，让你直接对接 Output 3。
