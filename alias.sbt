//
// Common Commands
// /////////////////////////////////
addCommandAlias("fullClean", ";project root ;+clean ;+cleanFiles")
addCommandAlias("cc", ";fullClean   ;+tc")
addCommandAlias("tc", "+test:compile")
addCommandAlias("ctc", ";clean ;cleanFiles ;tc")
addCommandAlias("to", "testOnly")
