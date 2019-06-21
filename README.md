**Problem Statement: **

>A bakery used to base the price of their produce on an individual item cost. So if a customer ordered 10
>cross buns then they would be charged 10x the cost of single bun. The bakery has decided to start
>selling their produce prepackaged in bunches and charging the customer on a per pack basis. So if the
>shop sold vegemite scroll in packs of 3 and 5 and a customer ordered 8 they would get a pack of 3 and
>a pack of 5. The bakery currently sells the following products:

**Name Code Packs**

**Vegemite Scroll VS5 3 @ $6.99**

5 @ $8.99

**Blueberry Muffin MB11 2 @ $9.95**

5 @ $16.95

8 @ $24.95

**Croissant CF 3 @ $5.95**

5 @ $9.95

9 @ $16.99


**Sample Input :** 
>3
>VS5 2
>3 6.99
>5 8.99
>MB11 3
>2 9.95
>5 16.95
>8 24.95
>CF 3
>3 5.95
>5 9.95
>9 16.99
>3
>10 VS5
>14 MB11
>13 CF

**Output:**
>10 VS5 $17.98
>2 X 5 8.99
>14 MB11 $54.80
>3 X 2 9.95
>1 X 8 24.95
>13 CF $25.85
>1 X 3 5.95
>2 X 5 9.95
