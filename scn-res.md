### ✅ Scenario 1（核心业务行为，**唯一必须的**）

```gherkin
Scenario: Fallback to historical price when mtm clean price is zero
  Given DefaultPrice = HISTORICAL
  And mtm clean price is 0.0 for a security
  When historical price lookup is executed
  Then the system attempts to retrieve the latest non-zero historical clean bid
  And if a historical price is found
    Then mtmPrice and mtmDirtyPrice are replaced with historical values
  And if no historical price is found
    Then mtmPrice remains 0.0
    And a warning is logged indicating zero price is used
  And the zero price event is recorded to zero_price.csv
```

🔹 优点：

* **一个 Scenario 覆盖完整决策树**
* 行为是“尝试 fallback”
* 结果是条件分支（found / not found）

---

### ✅ Scenario 2（非业务、但 leader 通常会允许留）

```gherkin
Scenario: Zero price logging failure does not block processing
  Given zero_price.csv is not writable
  When a zero price event is recorded
  Then an error is logged
  And price processing continues without interruption
```

🔹 这个 Scenario 的地位是：

* **Nice-to-have**
* 更偏 resilience / robustness
* 可以 later 再补 UT

---
