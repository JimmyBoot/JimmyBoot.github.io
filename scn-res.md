
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




Scenario: Zero price logging failure does not block processing
  Given zero_price.csv is not writable
  When a zero price event is recorded
  Then an error is logged
  And price processing continues without interruption
