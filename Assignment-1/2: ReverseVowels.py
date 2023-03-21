'''
2. ReverseVowels
Given a string, reverse the order of the vowels in the string.

Technique: Forward-Backward Two-Pointer
Time Complexity: O(N)
Space Complexity: O(1) --> Correction: O(N)
Time Taken: 30 minutes

'''
#a list of all the vowels
#sets 
vowels = ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

def reverseVowels(str):

    #convert string to a list of characters
    #set the pointers to the first and the last nodes
    str = list(str)
    front = 0
    back = len(str) - 1

    #while the pointers have not met
    while front < back:

        #if front and back are both a vowel, swap them
        if str[front] in vowels and str[back] in vowels:
            str[front], str[back] = str[back], str[front]
            front += 1
            back -= 1
        
        #if only front is a consonant, move its pointer ahead
        elif str[front] not in vowels:
            front += 1

        #if only back is a consonant, move its pointer backward
        elif str[back] not in vowels:
            back -= 1

    #rejoin the list into a string
    return "".join(str)

    

#Test Cases:

#The following 3 test cases were given in the spec.
print(reverseVowels("Uber Career Prep")) #Output: eber Ceraer PrUp
print(reverseVowels("xyz")) #Output: xyz
print(reverseVowels("flamingo")) #flominga

#Edge Cases:
# if an empty string is passed:
print(reverseVowels(""))  #Output: ""

# if a string with no vowels is passed:
print(reverseVowels("pmswdlt"))  #Output: "pmswdlt"

# if a string with only vowels is passed:
print(reverseVowels("aoueiauoee"))  #Output: "eeouaieuoa"

#one vowel
print(reverseVowels("a"))  #Output: "a"

print(reverseVowels("A"))  #Output: "A"

print(reverseVowels("EeEiIi"))  #Output: "iIiEeE"

#no letter
print(reverseVowels("1+2+3=6%7=6"))  #Output: "1+2+3=6%7=6"

#camel case
print(reverseVowels("Once Upon A Time"))  #Output: "enci Apon U TemO"

#long string
print(reverseVowels("This is a long string. I am sitting in a library and it is raining outside."))  
#Output: "Thes is u long string. i am sitting an a library ind it is raIning oatsidi."