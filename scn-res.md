
可以，虽然你不能直接装 Codex 或 Claude Code IDE，但用 VS Code + GitHub Copilot + GPT-5.4，已经能把效果做得很接近。关键不是“换个 IDE”，而是把 agent 式工作流 搭出来。

GitHub Copilot 在 VS Code 里本身就支持 agent mode，能跨多文件改代码、提议终端命令、根据结果继续迭代；同时 VS Code 现在也支持 custom instructions、prompt files、custom agents、agent skills、MCP servers 这类能力。也就是说，底层能力并不差，差的通常是你有没有把它“工程化”用起来。 ￼

我建议你把目标改成：

不是“像 Codex / Claude Code 那样用”，而是
“在受限环境里，把 Copilot 训练成一个稳定的本地 coding agent”。

⸻

一、先理解差距到底在哪

Codex/Claude Code 给人的强感受，通常来自这几件事：
	1.	会先做计划，再动手
	2.	会自己找相关文件
	3.	会改完后跑命令、看报错、继续修
	4.	会遵守项目约定，而不是每次重新解释
	5.	长任务能拆分，不是一问一答式聊天

其中 2、3、4、5，Copilot 在 VS Code 里其实都能部分做到；尤其 agent mode 就是为了“自主编辑 + 终端迭代”设计的。 ￼

所以你真正要补的是两个东西：
	•	上下文工程：让它更懂你的项目
	•	任务编排：让它按 agent 的方式工作，而不是一次性问答

⸻

二、最重要的做法：先把“项目说明书”固化下来

这是最值钱的一步。

VS Code / Copilot 官方推荐用 .github/copilot-instructions.md 或 *.instructions.md 这类文件式指令，而不是把要求散落在聊天里；而且旧的 settings 方式里一些 codeGeneration/testGeneration 指令已经标为 deprecated，更推荐文件式。 ￼

你可以在项目根目录放一个：

/.github/copilot-instructions.md

内容别写空话，直接写成可执行规则。比如你这种做 loader / batch / migration 项目，可以写成：

# Project coding instructions

## Goal
This project migrates legacy C++ loader behavior to Java/Spring Boot.

## Architecture
- Entry point is a one-shot batch-style launcher, not a long-running web app.
- Prefer clear service boundaries:
  - job orchestration
  - data reader/query
  - transformation/mapping
  - writer/persistence
- Avoid over-engineering.

## Coding rules
- Use Java 21.
- Prefer small methods and explicit naming.
- Do not introduce frameworks unless clearly justified.
- Keep config under src/main/resources/config.
- For DB access, follow existing repository/mapper style.
- Preserve legacy business semantics unless explicitly changed.

## Testing rules
- Add or update tests for each business scenario changed.
- Prefer scenario-oriented test names.
- For bug fixes, first reproduce with a test, then fix.
- Do not remove existing assertions unless obsolete.

## Batch behavior
- Always explain:
  - input source
  - transformation logic
  - output target
  - restartability impact
  - failure handling
- If Spring Batch is unnecessary, say so explicitly and provide a simpler alternative.

## Output format when asked to implement
1. Brief plan
2. Files to change
3. Code changes
4. Validation commands
5. Risks / assumptions

这个文件的作用，相当于给 Copilot 一个简化版的 “CLAUDE.md / agent policy”。GitHub 官方就是建议用 repo custom instructions 来告诉 Copilot 如何理解项目、如何 build/test/validate。 ￼

⸻

三、把每次提问都改成“代理任务单”，而不是普通聊天

你不要这样问：

帮我改一下这个 bug

要改成这种格式：

Task: Fix the root cause of [problem].

Requirements:
- First inspect relevant files and explain the likely cause.
- Then propose a minimal plan.
- Then implement the fix.
- Then run or suggest validation commands.
- If tests fail, iterate once and explain what changed.
- Do not refactor unrelated files.

Output:
- Plan
- Files changed
- Patch summary
- Validation result
- Remaining risk

这会明显更像 Codex / Claude Code，因为你是在逼它按 agent 流程输出。

再进一步，你可以给它固定三种任务模板：

1）修 bug 模板

Investigate this bug end-to-end.
1. Find likely root cause.
2. Identify the smallest safe fix.
3. Implement only necessary changes.
4. Add or update tests.
5. Show how to verify.

2）做功能模板

Implement this feature in plan-first mode.
1. Read relevant files.
2. Produce a short implementation plan.
3. Wait for no confirmation; proceed with the safest version.
4. Keep changes localized.
5. Include tests and validation steps.

3）重构模板

Refactor for clarity without changing behavior.
- Preserve public behavior.
- Keep method signatures stable unless necessary.
- Add tests before changing risky logic.
- Explain trade-offs.

这样做的本质，是把“Codex 的使用方式”迁移到 Copilot 上。

⸻

四、一定要用 Agent Mode，不要只用 Chat Mode

GitHub Copilot 官方说明里，agent mode 适合“给定任务后自主决定改哪些文件、提哪些命令，并根据结果继续修正”；而不是只回答问题。 ￼

所以你的默认工作方式应该是：
	•	问问题、理解代码：普通 chat
	•	要落地改代码：agent mode
	•	要它帮你跑验证、看结果、继续修：agent mode + terminal loop

你可以把很多任务都改成：

“Read the relevant files first, make a short plan, then implement with agent mode.”

这个习惯本身就会大幅接近 Codex / Claude Code。

⸻

五、把“终端循环”补上，这一步最像 Claude Code

Claude Code / Codex 强，很大程度是因为它们不是只写代码，而是会：
	•	改代码
	•	跑命令
	•	看报错
	•	再修

Copilot agent mode 也支持提议并执行终端相关流程。VS Code 最近版本还在强化 background agents / CLI handoff 这类体验。 ￼

所以你要给它明确的验证目标，比如：

After changes, validate with:
- ./mvnw -q -DskipTests compile
- ./mvnw test
If compilation or tests fail, inspect the error and make one focused fix iteration.

或者你们项目是 Gradle：

Validate with:
- ./gradlew compileJava
- ./gradlew test
- Explain any failing tests before changing unrelated code.

没有这一步，它就永远像“会说话的补全工具”；有了这一步，才像真正 agent。

⸻

六、建立“项目快捷提示词”文件，减少重复解释

VS Code 现在除了 custom instructions，还支持 prompt files、customization 体系。 ￼

你可以在仓库里建一个目录，比如：

.ai/prompts/
  bugfix.prompt.md
  feature.prompt.md
  bdd.prompt.md
  review.prompt.md

举个 bdd.prompt.md：

Task: Convert the requirement into semantic BDD scenarios.

Rules:
- Use Given/When/Then.
- Prefer business language over technical details.
- Include scenario outline when multiple data combinations exist.
- Separate happy path, validation path, and edge cases.
- If implementation gaps are visible, list them after the scenarios.

Output:
1. Business assumptions
2. Scenarios
3. Missing cases
4. Suggested test structure

这样你以后不是临时想 prompt，而是像调用“团队内部 agent skill”。

⸻

七、把上下文压缩成固定入口，让它更快懂你的代码库

Codex / Claude Code 的另一大优势，是它们很会“先读代码库”。
你在 Copilot 里也能人为补这个能力。

建议每个项目都准备这几个文件：
	•	README.md：项目目的、怎么启动
	•	ARCHITECTURE.md：模块职责、数据流
	•	TESTING.md：怎么编译、怎么跑测试、常见失败
	•	MIGRATION_NOTES.md：如果是遗留系统迁移，写清楚旧逻辑对应关系

因为 repo custom instructions 就是让 Copilot 更好理解你的项目、build 和 validate 方式。 ￼

对你这种工作内容，这个尤其重要。因为你的项目不是标准 CRUD，很多价值都在：
	•	legacy C++ 语义
	•	batch 启动方式
	•	数据库读写路径
	•	failure / restart / audit 取舍
	•	BDD 场景命名规范

这些如果不提前写进项目文档，每次都要重新教育模型。

⸻

八、学会“两阶段提问”：先计划，后执行

这是最接近 Codex / Claude Code 的使用习惯。

阶段 1：只要计划

Read the relevant code and produce:
1. root cause hypothesis
2. files likely involved
3. minimal implementation plan
4. validation approach
Do not edit code yet.

阶段 2：再执行

Proceed with the plan.
Keep changes minimal.
After implementation, validate and summarize any remaining risk.

这样会比“一口气让它全做完”稳定很多，尤其在公司电脑受限环境里。

⸻

九、尽量让它总是“基于文件”工作，而不是基于你口述

不要说：

我这里有个 loader，大概怎么怎么

要说：

Read peaceLoaderApplication, DummyJobConfig, LoaderProperties, and the existing batch config. Then propose the minimal change.

这会让它更像真正的 IDE agent，而不是聊天机器人。

⸻

十、如果公司允许，优先打开这些 VS Code / Copilot 能力

VS Code 官方文档显示，现在 Copilot 生态已经覆盖了不少“agent 化”能力，包括：
	•	custom instructions
	•	prompt files
	•	custom agents
	•	agent skills
	•	MCP servers  ￼

如果你公司环境允许，最值得优先开的顺序是：
	1.	Agent mode
	2.	Repo custom instructions
	3.	Prompt files
	4.	MCP server（比如文档、数据库 schema、内部工具）
	5.	Custom agents / agent skills

这里面前 3 个通常已经足够让体验上一个台阶。

⸻

十一、你现在的最佳落地方案

如果我是你，我会直接这么配：

目录结构

.github/
  copilot-instructions.md

.ai/
  prompts/
    bugfix.prompt.md
    feature.prompt.md
    bdd.prompt.md
    review.prompt.md

docs/
  ARCHITECTURE.md
  TESTING.md
  MIGRATION_NOTES.md

固定工作流

每个任务都走这 5 步：
	1.	Read first
先让它读相关文件，不立刻写
	2.	Plan first
先输出短计划
	3.	Implement minimally
只做最小必要修改
	4.	Validate
跑 compile / test / lint
	5.	Summarize risk
总结假设和风险

固定提示词

每次都加一句：

Follow repository instructions.
Read relevant files first.
Prefer minimal safe changes.
Validate before concluding.

这句非常有用。

⸻

十二、你要接受的现实：能接近，但不完全一样

你现在这套环境，和 Codex / Claude Code 相比，主要还差在：
	•	并行多代理能力：Codex 强调 cloud tasks / parallel workflows。 ￼
	•	更深的终端原生体验：Claude Code 本质上就是 terminal-first 的 agent。 ￼
	•	更强的长任务持续性：Claude Code 近来的更新也在强化 agent teams 和更长上下文任务。 ￼

但你完全可以把日常 70%～85% 的价值做出来，尤其是：
	•	理解代码库
	•	生成修改计划
	•	多文件小范围改动
	•	生成测试
	•	编译测试迭代
	•	重构和 review

对大多数公司内开发，其实已经够用了。

⸻

十三、最适合你当前工作的具体玩法

结合你现在偏 Java / Spring Boot / Batch / migration 的工作，我建议你把 Copilot 当成这 4 类 agent 用：

1）Legacy migration agent

让它专门做：
	•	读取旧 C++ 逻辑
	•	提炼业务语义
	•	输出 Java 迁移计划
	•	列出风险点

提示词：

Act as a legacy-to-Java migration agent.
Preserve business semantics.
Explain old behavior, target Java design, and migration risks.

2）BDD scenario agent

让它专门做：
	•	业务需求转 Gherkin
	•	区分 happy path / edge case / validation case
	•	建议 test data

3）Batch validation agent

让它专门做：
	•	梳理 job 参数
	•	启动方式
	•	restart / audit / execution metadata 是否必要
	•	验证命令

4）Refactoring reviewer

让它专门做：
	•	只 review，不直接大改
	•	找 naming / coupling / test gaps

这就是“没有 Claude Code / Codex，也自己造一套”。

⸻

十四、给你一个可以直接复制的总提示词

你可以把下面这段作为日常总模板：

Follow repository instructions and read relevant files first.

Work in agent style:
1. Understand the task and inspect the most relevant files.
2. Produce a short plan before editing.
3. Make the smallest safe implementation.
4. Add or update tests when behavior changes.
5. Validate with build/test commands.
6. Summarize what changed, what was verified, and any remaining risk.

Constraints:
- Do not refactor unrelated areas.
- Preserve existing business behavior unless explicitly asked.
- Prefer clarity over cleverness.
- If information is missing, make the safest assumption and state it.

这段就很接近“把 Copilot 训成半个 Codex”。

⸻

结论

你的限制条件下，最有效的路径不是纠结工具名，而是把这 4 件事做到位：

1）开启并优先使用 agent mode
2）在仓库里放 .github/copilot-instructions.md
3）建立固定 prompt 模板和项目文档
4）强制它走“读代码 → 计划 → 修改 → 验证”的闭环

这样做以后，体验会从“高级补全”变成“弱化版 coding agent”，已经很接近 Codex / Claude Code 的核心价值了。相关能力在 VS Code / GitHub Copilot 官方文档里都是明确支持的。 ￼

你要的话，我下一条可以直接帮你生成一份适合你当前 Java/Spring Batch 迁移项目的 copilot-instructions.md 模板。
