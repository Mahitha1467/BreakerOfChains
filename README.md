# BreakerOfChains

Problem Statement: 
The other kingdoms in the Universe also yearn to be the ruler of Southeros and war is imminent! The High Priest of Southeros
intervenes and is trying hard to avoid a war and he suggests a ballot system to decide the ruler.
Your coding challenge is to help the High Priest choose the ruler of Southeros through the ballot system.
Rules of the Ballot system
1. Any kingdom can compete to be the ruler. 
2. They should send a message to all other kingdoms asking for allegiance. 
3. This message will be put in a ballot from which the High Priest will pick 6 random messages.  4. The High Priest then hands over the 6 messages to the respective receiving kingdoms. 
5. The kingdom that receives the highest number of allegiance is the ruler.
© 2018 geektrust.in. All rights reserved.
 
Rules to decide allegiance by a kingdom
1. The receiving kingdom has to give allegiance to the sending kingdom if the message contains the letters of the animal in their emblem (same as https://github.com/Mahitha1467/AGoldenCrown).
2. If the receiving kingdom is competing to be the ruler, they will not give their allegiance even if the message they received is correct. 3. A kingdom cannot give their allegiance twice. If they have given their allegiance once, they will not give their allegiance again even
if they get a second message and the message is correct.  In case there is a tie
1. If there is a tie, the whole ballot process is repeated but only with the tied kingdoms till there is a winner. 
Sending messages
The format of the message dropped in the ballot should contain :
• The Sender kingdom
• The Receiver kingdom
• The Message (should be selected randomly from the messages provided in the table below)  

Sample Input and Output
Who is the ruler of Southeros? Output: None
Allies of Ruler?
Output: None
Enter the kingdoms competing to be the ruler: Input: Ice Space Air

Results after round one ballot count Output: 
Output: Allies for Ice : 2
Output: Allies for Space: 1 
Output: Allies for Air: 0

Who is the ruler of Southeros? 
Output: Ice
Allies of Ruler?
Output: Land Fire

Software Dependencies: Java8
How to add Libraries in Intellij : https://www.jetbrains.com/help/idea/library.html
Test Dependencies: 
powermock-api-mockito:1.6.5
powermock-module-junit4:1.6.5

or required jars can be found in lib folder

IDE support:  IntelliJ IDEA

How to run the application:
Run the main method which is in MainClass
