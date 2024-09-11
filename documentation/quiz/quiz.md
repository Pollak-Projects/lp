# LearningPulse Quiz documentation

> **❗ Important!**  
> This documentation may change in the future.



Teachers can creater quizzes that students may fill out as many times as the teache sets the maximum amount for filling out. The tests are server-side only and students may only know the answer for them upon finishing them. Showing the correct answers after the test can also be disabled by the teacher to prevent cheating.

## Question types

- ### Text input

  A basic text input field with a title for the question. A character or word limit can also be set.

  **Rewarding:**  
  Depends. The teacher may give the field a maximum achievable point that user can see when filling out. Then after the test is sent in teachers may grade accordingly.

  #### Implementation
    The input field will be put into a "long" datatype

- ### Basic select

  A list of radio buttons are shown where only one may be selected.

  **Rewarding:**  
  One point by default, can be modified by the teacher.

  #### Implementation
    The radio button titles will be put into an "array/list" datatype, then when the user inputs the answer, the picked number's index in the "array/list" will be put into an "int" value 

- ### Complex select

  A list of checkboxes are show where multiple (or all) can be selected at once.
  The creator of the quiz may also create limits on the concurrent selections.

  **Rewarding:**  
  As many points as many good answers.

    #### Implementation
    The titles will be put into an "array/list", then each value will be assigned to a checkbox. The indexes of the selected checkboxes will be teh n put into an "int[]".

- ### Pair match

  An even or odd number of cards are given that can either be an image or text. Or vise versa. Not every card has to be paired, there can be bonus cards.
  The user has to pair these cards. The order does not matter only if they match.

  **Rewarding:**  
  One point for each right selected pair by default, can be modified by the teacher.

  #### Implementation
  The titles will be put into a dictionary, with each pair having the same "value" set.

- ### Ordering

  A list of cards of texts are shown, the user has to order them from top to bottom, or bottom to top, according to the teacher's instructions.

  #### Rewarding:

  One point for every correctly ordered card by default, can be modified by the teacher.
  #### Implementation
    The titles will be put into an "array/list" and will be ranked from 0 - any by the "array/list" index. When the question appears, the items will be randomly moved from thei position.

- ### File upload(s)

  A field where users can select file(s) from their own drive or conputer and upload them to the question.

  Automatic evaluation is not possible with this type of question.

  #### Rewarding:

  Entirely dependent on the teacher's personal evaluation. They will have to decide if the work is adequate of the constrains the teacher has set or not.
    #### Implementation
    I have no fucking clue


