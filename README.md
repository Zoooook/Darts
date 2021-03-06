The actual stats were crunched by [these guys](http://www.stat.cmu.edu/~ryantibs/darts/index.html). The assumption is of a bivariate normal distribution, and in the simple model there is no horizontal-vertical interaction and it's radially symmetric. Also the distribution remains constant no matter where you aim on the dartboard (I doubt it, my accuracy is worse when I aim higher because I have to throw the darts harder). There's a discussion of why these assumptions are reasonable [here](http://www.dartsassistant.com/PDF/jors1982191a.pdf).

The bit I'm interested in is the measurement of a player's accuracy. The primary indicator here is sigma (&#963;), the standard deviation, measured in millimeters. Lower is better. [This guy](http://datagenetics.com/blog/january12012/index.html) mostly repeats info from the first link, but there are a couple interesting images there: [Optimal aiming point based on &#963;](http://datagenetics.com/blog/january12012/movement.png) and [Maximum expected score](http://datagenetics.com/blog/january12012/elbow.png).

The second link has this gem in it: "It is not in general possible to determine a player's &#963; to an accuracy of one decimal place [in inches]. Even if he did take the trouble to calculate his &#963; to this precision on one occasion, it would probably be different at another time. The accuracy of a dart player is affected by many factors: the distance from the board [7 feet 9.25 inches], the darts themselves, the visibility in the smoke-filled rooms in which the game is usually played -- to say nothing of the player's degree of inebriation."

The first link has a java applet where you can input your scores from a number of throws and it will tell you your &#963;. Unfortunately, with the current state of Java, this requires going through Java version and Java installer hell, then Java security hell because the applet is unsigned and the Java control panel doesn't actually obey your settings, and then Windows registry hell when Java screws up your computer and you [can't install Java and can't uninstall Java](https://www.java.com/en/download/help/error_1603.xml).

Luckily, they also provide the source code for the applet. I've distilled all the relevant bits from Stats.java and there's not much code left. It uses [this algorithm](http://en.wikipedia.org/wiki/Expectation%E2%80%93maximization_algorithm) and some math to calculate your accuracy.

I've also modified it a bit. The original code just took your raw scores, then essentially had to guess (average) the possible locations you could have hit. For example an 18 could be a single 18 (either board region), a double 9, or a triple 6. Then it threw out all the angular data and just calculated based on the probable distance from the center. I've eliminated this roundabout method and based the calculations directly on the distance from the center (also this splits out the inner single and the outer single ring). All you have to do now is keep track of which ring your darts land in. Also I've changed it so you can just input the totals from each category instead of listing each dart separately. [These guys](http://www.med.mcgill.ca/epidemiology/hanley/tmp/Likelihood/letter-jrssa-20110509.pdf) had the same idea.

There's a place to input the measurements from your own board (use any units you want, the &#963; output will be in those units), and there's a place to input your thrown dart results. Obviously the more darts you throw, the better your &#963; estimate will be. You don't have to pad your results with zeros, just close the array. The code is general and can support any bullseye/ring system, just input the measurements from the center to each boundary (make sure the first number is 0 and the last is -1), and record your results on that system. I added a measurement for the edge of the board, and you can add more if you want. As far as the code is concerned, any darts you threw outside the largest boundary might as well have been thrown across the street (they impact your accuracy measurement by a whole bunch).

I've also translated it to JavaScript and given it an [interface](http://jsfiddle.net/5xb766p8/).

---

References in case of link rot:

A Statistician Plays Darts -- Ryan Tibshirani, Andrew Price and Jonathan Taylor  
Optimal Strategies for the Game of Darts -- David Kohler  
Practical and efficient estimates of one’s accuracy in darts -- Sudipta Sadhukhan, Zhihui Liu, and James A. Hanley
