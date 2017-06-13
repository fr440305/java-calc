
Rem Question2 - runner.bat
Rem Dev By __HUO_YU__
javac -d ./ "Q1/QuestionOne.java" "Q1\Stack.java" "Q1\ArrayStack.java" "Q1\ListStack.java"
pause
cls
java QuestionOne
del *.class *.java~ *.un~ *.bat~ >nul 2>nul
