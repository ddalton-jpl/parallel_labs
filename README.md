TODO FINISH READ ME

Description FOR HWK2
1. write a concurrent version of Prime Finder
2. Outputs the total number of prime numbers within the range 1 to m
3. The program uses n threads
4. Make ReadMe for compile
5. Length of subrange in m / n
6. Create a counter class
7. Call total() in the Counter object
8. Compare cost of seriel excecution vs concurrent excecution


Steps to SOLVE hwk2
- create a counter class that increases a count for the function
    - counter will need to have some sort of Object locking to avoid exceptions
    - need to call total() in counter object
- write a function that outputs the total number of prime numbers between a range between 1 to m
    - loop through and implement some prime math stuff up to M
    - have it set up so the threads pass through the subrange m / n
- write a function that creates N number of threads
- Compare time costs and print time costs