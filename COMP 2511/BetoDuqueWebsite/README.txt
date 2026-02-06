1. Document the issues you identified in each of the four categories (Perceivable, Operable, Understandable and Robust) as discovered through the manual and automated analyses above.

1a. Perceivability: 
I have provided alts for images, but have not included titles, captions, or transcripts. 
Each image does have an appropriately high contrast ratio, and are related to the content that it represents (the images I use are mostly for thumbnails of a webpage). 
There are no videos or audio in my website. 
I used semantic tags throughout and headers when necessary. 
I did not use any tables in my website. 
My forms do not use any <legends> (they would not fit in aesthetically in the way I used them).
The lowest contrast ratio with text on my website is 6.45:1 (#CCCCCC foreground, #404040 background). Every other color contrast is higher.

1b. Operable:
In full honesty, my website is NOT very operable. The tab button only focuses on <a> tags.
Clicking on labels makes the browser focus on the appropriate input tag.
I did not modify the focus subclass as I thought it did the job well enough.
I don't have a 'Skip to the main' button (there are not many links to iterate through).

1c. Understandable:
The text is easy to read and has an appropriate level of contrast.
The links are very distinct, each link (that is not in the nav bar) is pink and underscored.
By default, each link will change the cursor to a pointer style.

1d. Robust:
As part of the assignment, the website adapts to a user's device size and changes formatting, font size, etc. to be readable.

1e. Automated analysis:
Not gonna lie, I couldn't use the WAVE chrome extension to check for accessibility.




2. Describe the kind of changes you made. Don’t just list them; instead generalize the types of issues that you created when building your website, and then describe the generalized type of changes that you made to resolve those issues.  

2a. The biggest issue I kept having when I made this website was regarding the padding on each article and class="information" sections on small (mobile) devices. For some reason, it would expand to be larger than 100% of the screen width, which gave the website a horizontal scrolling bar.

-> To fix this, I simply moved all of the media queries into each html file's css file. For some reason, this just fixed everything and was very convenient. I have no idea why it worked but welcome to programming I guess!

2b. Another issue I had was the header looking weird on mobile devices; specifically the logo. It took me a concerning amount of time to realize that <figure> tags are block  elements, which made whatever image I put in them to float to the right (as it should because of flow). To fix this, I put in "figure a {display: flex; justify-content: center;} to center the logo. I selected the <a> tag because I wanted the logo to lead to the index.

