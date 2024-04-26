This app looks at some good practices in design and implementation of an Android app.

<b>DI vs Factory classes</b> 
Alternate to using DI libraries is the factory class(es) that is used on the main branch. 
For eg. RepositoryFactory and TodoUseCasesFactory classes which
are responsible for providing instance of respective class to the ViewModel for example.

<b>Dagger2 in Multi-module project</b>
dagger2 branch of GoldenSample app has a multi-module project where ITodoRepository instance is
provided via the Dagger2 library. Here the idea is to use Dagger in a multi-module project. 
For this i have added a Module class for each sub-project.

Both RoomModule and TodoUseCaseModule are then included in the parent module (ui/di/TodoModule.kt). 
The Module(includes = []) tells Dagger2 what other Modules the TodoModule is composed of. 
It is some kind of Module dependency.

To expand a bit on Dagger2 in multi-module project - 
Components and Sub-components-
Sub-components extend from a main (application?) component to provide specific set of dependencies
via modules. They can have their own lifecycle scope which is tied to a particular
activity/fragment. The benefit of sub-components is that one can modularize dependencies to a group
of classes. Also the lifecycle of instance of these classes can be tied to the Activity/fragment in
which the sub-component is built. In my case, i started off with sub-components in each module which
had their own factory interface. Which is also a valid approach, but when i discovered Module
includes, then i did not feel it necessary to have a sub-component for each module.

Component Dependencies - Components can declare dependencies to other Components via @Component(
dependencies = [])

Component Builder and Factory - SubComponent.Factory provides an interface with a method that
returns instance of the component. But there is a Builder interface as well which can be used to
provide instance of the component.

Component dependencies vs Module includes??
Components provide ability to define custom scope. So classes using the same custom scope 
will be instantiated once during the lifecycle scope. Module dependency does not provide 
any such feature. So based on the requirement, one should consider using component dependency 
or module dependency. 

