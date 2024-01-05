# A3: Dice Play App

An Android application offering users two gaming options, along with a separate feature showcasing details of the second game.

### a. Project Details

**Project Name**: Dice Play Mobile Application

**Participants**: Ayush Bhardwaj (2021A7PS2634G, f20212634@goa.bits-pilani.ac.in) - Naman Agarwal (2021A7PS2668G, f20212668@goa.bits-pilani.ac.in)

### b. App Overview

The app hosts two distinct games across three interfaces. Users can either tap a dice icon, receiving 5 coins each time a 6 is rolled or participate in a game where they stake coins on specific combinations to earn rewards.

However, there are some noted restrictions:

- Extensive dice rolls can lead to overlapping text in the coin label or even go beyond the display, impairing readability.
- Excessive rolls may also surpass JAVA's maximum integer range, causing potential app glitches.
- Rapid taps on the dice icon may lead to system lag or interrupted feedback.
- Initially, the dice displays a '0' before any roll.

It's worth noting that these issues are rare and don't compromise the app's core functions.

### c. Task Implementation
#### Task 1:
Within the Wallet Feature, our focus was on streamlined operation. Functions are hosted in the ViewModel for simplicity. User prompts were enabled using Toast messages. For the "Two or More" option, we utilized a single ViewModel to maintain data uniformity. User's coin count is smoothly transferred to the "TwoOrMoreActivity" via intent.

#### Task 2:
For the "Two Or More" feature, Java functions were crafted specifically for the view models. We opted for a list model for the four dice, while stakes were treated differently. Game types were discerned via a case-based approach, aligning with the GameType enum. This also included important validations for a frictionless user experience.

#### Task 3:
Adopting strategies from prior tasks and the earlier Wallet Feature, we identified the selected radio button choice. This data informed the game type selection. Relevant UI testing was also incorporated.

#### Task 4:
A back navigation capability was integrated using intent, preserving current progress. The Wallet Feature was subsequently updated with this data before wrapping up the present view.

#### Task 5:
Drawing inspiration from "Two or More," we debuted an "Info" feature. This housed game instructions and a navigational back button, functioning in tandem with the dedicated "Back Activity."

#### Task 6:
The app's horizon broadened with three fresh landscape views, mirroring existing interfaces but enhanced for landscape viewing.

#### Task 7:
Regular UI description updates fortified navigation and accessibility. Any identified shortcomings from Accessibility scanner tests across activities were addressed. Espresso Test scenarios were devised to transition between layouts and validate UI elements in the resulting screen.

### d. Evaluation

Our development sequence prioritized app creation and was complemented by tailored test cases rooted in the app's specifications. In total, we formulated 10 test scenarios, half of which leveraged Espresso and the other half used Mockito.

### e. Time Investment

**Coding, Testing, and Rectifying Accessibility Concerns**: 13 hours

**Documentation Creation**: 1 hour

**Overall Duration**: 14 hours

### f. Complexity Assessment

As our development acumen grew, so did our efficiency. Introducing new activities and managing related Intents posed the main challenges, leading us to grade the experience as 8 out of 10.
