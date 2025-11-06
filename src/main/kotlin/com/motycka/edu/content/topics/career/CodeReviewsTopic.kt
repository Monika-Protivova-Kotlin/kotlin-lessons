package com.motycka.edu.content.topics.career

import com.motycka.edu.model.*
import kotlinx.html.*

object CodeReviewsTopic : Topic(
    title = "Code Reviews",
    slides = listOf(
        CodeReviewIntroSlide,
        WhatToReviewSlide,
        GivingFeedbackSlide,
        ReceivingFeedbackSlide,
//        CodeReviewBestPracticesSlide,
//        CodeReviewToolsSlide
    )
)

object CodeReviewIntroSlide : Slide(
    header = "Why Code Reviews Matter",
    summary = {
        +"Code reviews are one of the most valuable practices in software development for catching bugs, sharing knowledge, and maintaining code quality."
    },
    content = {
        p {
            h4 { +"Benefits of code reviews" }
        }
        ul {
            li {
                strong { +"Catch bugs before production" }
                br()
                +"Multiple eyes on code find issues that tests miss"
            }
            li {
                strong { +"Share knowledge" }
                br()
                +"Team members learn about different parts of the codebase"
            }
            li {
                strong { +"Maintain consistency" }
                br()
                +"Ensure code follows team standards and architectural patterns"
            }
            li {
                strong { +"Improve code quality" }
                br()
                +"Discussion leads to better solutions and cleaner code"
            }
            li {
                strong { +"Mentorship opportunity" }
                br()
                +"Junior developers learn from senior developers' feedback, or by reviewing seniors' code"
            }
            li {
                strong { +"Collective ownership" }
                br()
                +"Everyone understands and takes responsibility for the codebase"
            }
        }

        hintCard {
            +"Code reviews are not about finding fault - they're about collaborative improvement and learning together."
        }
    }
)

object WhatToReviewSlide : Slide(
    header = "What to Look For in Code Reviews",
    summary = {
        +"Effective code reviews focus on both technical correctness and broader software engineering principles."
    },
    content = {
        twoColumns(
            left = {
                p { strong { +"Technical Aspects:" } }
                ul {
                    li {
                        strong { +"Correctness" }
                        br()
                        +"Does the code do what it's supposed to do?"
                    }
                    li {
                        strong { +"Tests" }
                        br()
                        +"Are there appropriate tests? Do they cover edge cases?"
                    }
                    li {
                        strong { +"Error handling" }
                        br()
                        +"Are errors handled appropriately?"
                    }
                    li {
                        strong { +"Performance" }
                        br()
                        +"Are there obvious performance issues?"
                    }
                    li {
                        strong { +"Security" }
                        br()
                        +"Are there security vulnerabilities?"
                    }
                    li {
                        strong { +"Edge cases" }
                        br()
                        +"What happens with null, empty, or extreme values?"
                    }
                }
            },
            right = {
                p { strong { +"Design & Maintainability:" } }
                ul {
                    li {
                        strong { +"Readability" }
                        br()
                        +"Is the code easy to understand?"
                    }
                    li {
                        strong { +"Architecture" }
                        br()
                        +"Does it fit the overall design?"
                    }
                    li {
                        strong { +"SOLID principles" }
                        br()
                        +"Is the code well-designed and maintainable?"
                    }
                    li {
                        strong { +"DRY" }
                        br()
                        +"Is there unnecessary duplication?"
                    }
                    li {
                        strong { +"Naming" }
                        br()
                        +"Are names clear and descriptive?"
                    }
                    li {
                        strong { +"Documentation" }
                        br()
                        +"Is complex logic explained?"
                    }
                }
            }
        )
    }
)

object GivingFeedbackSlide : Slide(
    header = "How to Give Constructive Feedback",
    summary = {
        +"Effective code review feedback is specific, kind, and focused on the code, not the person."
    },
    content = {
        p { strong { +"Principles for good feedback:" } }

        twoColumns(
            left = {
                h4 { +"Do" }
                ul {
                    li {
                        strong { +"Be specific" }
                        br()
                        +"\"This function could be split into smaller functions\" vs \"This is messy\""
                    }
                    li {
                        strong { +"Ask questions" }
                        br()
                        +"\"Could we use a map here instead of filtering twice?\""
                    }
                    li {
                        strong { +"Explain why" }
                        br()
                        +"\"This could cause a race condition because...\""
                    }
                    li {
                        strong { +"Acknowledge good code" }
                        br()
                        +"\"Nice handling of this edge case!\""
                    }
                    li {
                        strong { +"Suggest alternatives" }
                        br()
                        +"\"What about trying X approach?\""
                    }
                    li {
                        strong { +"Distinguish required vs optional" }
                        br()
                        +"\"nit:\" for style, \"BLOCKER:\" for must-fix"
                    }
                }
            },
            right = {
                h4 { +"Don't" }
                ul {
                    li {
                        strong { +"Be vague" }
                        br()
                        +"\"This doesn't look right\""
                    }
                    li {
                        strong { +"Make it personal" }
                        br()
                        +"\"Why did you write it this way?\""
                    }
                    li {
                        strong { +"Nitpick everything" }
                        br()
                        +"Focus on important issues"
                    }
                    li {
                        strong { +"Assume incompetence" }
                        br()
                        +"Maybe they know something you don't"
                    }
                    li {
                        strong { +"Rewrite in your style" }
                        br()
                        +"Accept different valid approaches"
                    }
                    li {
                        strong { +"Only criticize" }
                        br()
                        +"Point out good things too"
                    }
                }
            }
        )

        importantCard {
            +"Remember: You're reviewing code, not judging the person. Frame feedback as collaborative problem-solving."
        }
    }
)

object ReceivingFeedbackSlide : Slide(
    header = "How to Receive Feedback Professionally",
    summary = {
        +"Receiving code review feedback gracefully is a key professional skill that accelerates your growth."
    },
    content = {
//        p { strong { +"Mindset for receiving feedback:" } }
        ul {
            li {
                highlight { +"Assume good intent" }
                +" - Reviewers are trying to help improve the code, not criticize you personally"
            }
            li {
                highlight { +"Ask for clarification" }
                +" - If feedback is unclear, ask questions rather than getting defensive"
            }
            li {
                highlight { +"Explain your reasoning" }
                +" - If you disagree, explain your thinking: \"I chose this approach because...\""
            }
            li {
                highlight { +"Be open to learning" }
                +" - Even senior developers learn from reviews. Every reviewer has different experience"
            }
            li {
                highlight { +"Separate ego from code" }
                +" - Your code is not you. Better code benefits everyone"
            }
            li {
                highlight { +"You may decide not to make a change based on feedback" }
                +" - That's okay if you have a good reason. Just explain your choice respectfully"
            }
            li {
                highlight { +"Thank reviewers" }
                +" - Someone took time to help improve your work. Acknowledge that"
            }
        }
        twoColumns(
            left = {
                p { h4 { +"Good responses" } }
                ul {
                    li { +"\"Good catch! I'll fix that\"" }
                    li { +"\"Could you explain more about why?\"" }
                    li { +"\"I hadn't considered that case. Thanks!\"" }
                    li { +"\"I chose X because Y, but I'm open to alternatives\"" }
                    li { +"\"You're right, but I will do it in separate branch.\"" }
                }
            },
            right = {
                p { h4 { +"Avoid" } }
                ul {
                    li { +"\"That's just your opinion\"" }
                    li { +"\"It works, doesn't it?\"" }
                    li { +"\"I didn't have time to do it properly\"" }
                    li { +"Silence or ignoring feedback" }
                }
            }
        )
    }
)

object CodeReviewBestPracticesSlide : Slide(
    header = "Code Review Best Practices",
    summary = {
        +"Following established practices makes code reviews more effective and less painful for everyone."
    },
    content = {
        twoColumns(
            left = {
                h4 { +"For Authors:" }
                ul {
                    li {
                        strong { +"Keep changes small" }
                        br()
                        +"< 400 lines is ideal. Large PRs don't get thorough reviews"
                    }
                    li {
                        strong { +"Write clear descriptions" }
                        br()
                        +"Explain what, why, and how. Link to tickets/issues"
                    }
                    li {
                        strong { +"Self-review first" }
                        br()
                        +"Review your own changes before requesting review"
                    }
                    li {
                        strong { +"Respond promptly" }
                        br()
                        +"Don't leave reviewers waiting days for responses"
                    }
                    li {
                        strong { +"Test thoroughly" }
                        br()
                        +"Don't submit code you know has issues"
                    }
                    li {
                        strong { +"Add context" }
                        br()
                        +"Screenshots, examples, or test output help"
                    }
                }
            },
            right = {
                h4 { +"For Reviewers:" }
                ul {
                    li {
                        strong { +"Review promptly" }
                        br()
                        +"Don't leave PRs sitting for days"
                    }
                    li {
                        strong { +"Actually test the code" }
                        br()
                        +"Pull it down and run it when possible"
                    }
                    li {
                        strong { +"Focus on important issues" }
                        br()
                        +"Don't nitpick formatting if there are real bugs"
                    }
                    li {
                        strong { +"Finish your review" }
                        br()
                        +"Don't leave comments and disappear"
                    }
                    li {
                        strong { +"Be timely" }
                        br()
                        +"Review within 24 hours if possible"
                    }
                    li {
                        strong { +"Approve when ready" }
                        br()
                        +"Don't hold up perfect code with minor nits"
                    }
                }
            }
        )

        warningCard {
            +"Code reviews should not be a bottleneck. If PRs sit unreviewed for days, the team needs to address the process."
        }
    }
)

object CodeReviewToolsSlide : Slide(
    header = "Code Review Tools & Workflows",
    summary = {
        +"Modern development platforms provide excellent tools for code reviews through pull/merge requests."
    },
    content = {
        p { strong { +"Common platforms:" } }
        ul {
            li {
                strong { +"GitHub Pull Requests" }
                br()
                +"Most widely used. Supports inline comments, suggested changes, reviews, CI integration"
            }
            li {
                strong { +"GitLab Merge Requests" }
                br()
                +"Similar to GitHub with additional features for larger teams"
            }
            li {
                strong { +"Bitbucket Pull Requests" }
                br()
                +"Enterprise-focused with Jira integration"
            }
            li {
                strong { +"Azure DevOps" }
                br()
                +"Microsoft's platform with deep Azure integration"
            }
        }

        p { strong { +"Key features to use:" } }
        twoColumns(
            left = {
                ul {
                    li {
                        strong { +"Inline comments" }
                        br()
                        +"Comment on specific lines of code"
                    }
                    li {
                        strong { +"Suggested changes" }
                        br()
                        +"Propose code changes that can be accepted with one click"
                    }
                    li {
                        strong { +"Review status" }
                        br()
                        +"Approve, request changes, or comment"
                    }
                    li {
                        strong { +"CI/CD integration" }
                        br()
                        +"Automated tests must pass before merge"
                    }
                }
            },
            right = {
                ul {
                    li {
                        strong { +"Protected branches" }
                        br()
                        +"Require reviews before merging to main"
                    }
                    li {
                        strong { +"Code owners" }
                        br()
                        +"Automatically request reviews from experts"
                    }
                    li {
                        strong { +"Draft PRs" }
                        br()
                        +"Share work-in-progress for early feedback"
                    }
                    li {
                        strong { +"Review threads" }
                        br()
                        +"Mark conversations as resolved"
                    }
                }
            }
        )

        hintCard {
            +"Set up branch protection rules to require at least one approval before merging. This ensures code reviews actually happen."
        }
    }
)
