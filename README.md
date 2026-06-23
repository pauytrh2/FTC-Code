# FTC Base Code
> Prismatics's base code for an FTC robot

## Good principles to follow
Generally, this code follows the [KISS](https://en.wikipedia.org/wiki/KISS_principle) principle.

### Filenames
Start every Java file's name with "M_" if it is manually controlled, or an "A_" if it is automatic.

For example:
```java
A_Main.java // this file is for an automatic OpMode
M_Leg.java // this file is for a manual OpMode 
```
### Other
Every `public class` that `extends LinearOpMode` (an `OpMode`) should be annotated with
```java
@Autonomous(name="A_Something", group="Linear OpMode") // if it's autonomous, or
@TeleOp(name="M_Something", group="Linear OpMode") // if it's manual
```
