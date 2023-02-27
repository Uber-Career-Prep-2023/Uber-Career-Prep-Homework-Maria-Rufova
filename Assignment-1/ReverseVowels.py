'''
2. ReverseVowels
Given a string, reverse the order of the vowels in the string.

Technique: Forward-Backward Two-Pointer
Time Complexity: O(n)
Space Complexity: O(1)
Time Taken: 30 minutes

'''
def reverseVowels(str):

    #a list of all the vowels
    vowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']

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