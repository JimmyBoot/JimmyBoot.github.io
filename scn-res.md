You are a senior backend engineer helping with a C++ → Java migration.

Your task:
Derive BDD scenarios from the given code.

Context:
- The scenarios will be used to validate functional parity during migration.
- They will later drive unit tests and Cucumber tests.
- We care about observable business behavior, NOT implementation details.

Input:
I will provide a code snippet.
- Focus ONLY on business decision logic and data transformation.
- Ignore configuration loading, country-specific wiring, logging, or framework code.

Output requirements:
- Write BDD scenarios using Given / When / Then.
- Use business-level language, no method names or class names.
- Each scenario should represent a meaningful behavior, not a single if-statement.
- Avoid over-splitting scenarios; prefer combining related branches when possible.
- If multiple conditions lead to the same outcome, express them in one scenario.

Format:
- Scenario title (clear business intent)
- Given: system state and relevant input data (use tables if helpful)
- When: triggering action
- Then: expected output and invariants

Constraints:
- Do NOT repeat the code.
- Do NOT invent new behavior not present in the code.
- If logic is unclear, state assumptions explicitly.

Code:
[PASTE CODE HERE]
