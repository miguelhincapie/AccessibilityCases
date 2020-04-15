# Accessibility Cases on Android
There are different kind of accessibility services that you can implement depends on your app's needs. I summarized them and a bunch of programming edge cases that I have had to face in multiple projects… almost all of them made me scream at least once.<br>
* How to turn ON/OFF TalkBack accessibility service through my app
* How to send accessibility focus to a specific UI element after TalkBack gets started
* Accessibility with mvvm and delegate pattern
* Navigate through a Grid
* Navigating through Carousel (an horizontal RecyclerView)
* Disabling buttons with accessibility
* Different TalkBack ports and Android versions
<br><br>
<img width="300" src="https://github.com/miguelhincapie/AccessibilityCases/blob/master/docs/diagrams/mainview.png">  
### architecture
MVVM with accessibility interaction.<br>
<img width="400" eight="300" src="https://github.com/miguelhincapie/AccessibilityCases/blob/master/docs/diagrams/architecture.png">


### Branches
* hello_accessibility: used to explain basic accessibility xml attributes.
* master: contains almost all edge cases explained in section 2 of <medium post>

### Libraries
* Dagger2
* AndroidX
* Android accessibility suite

### License
Copyright 2020 [Miguel Hincapie C]<br>
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at <br>

http://www.apache.org/licenses/LICENSE-2.0<br>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
<br>

https://stackoverflow.com/users/1332549/miguelhincapiec <br>
https://github.com/miguelhincapie <br>
https://www.linkedin.com/in/miguelhincapie/<br>
