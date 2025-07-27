WinWaitActive("Open") ;Wait until the 'Open' dialog appears
Sleep(1000) ; Wait 1 second
Send("C:\Users\prath\IdeaProjects\ChartInk\src\main\resources\data\runTime_Stocks_for_ST1_Cndt2.txt")
Sleep(500)
Send("{ENTER}") ; Press the Open button