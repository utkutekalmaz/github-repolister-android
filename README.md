# User Repo App

The app lists the repositories of the user given and a few details about the selected repository. Using the Model-View-Presenter pattern, and some third party libraries.

## App structure:

```
|
├──adapter
    ├──ReposAdapter.java
├──constants
    ├──AppConstants.java
├──model
    ├──Owner.java
    ├──RepoItem.java
├──network
    ├──ApiClient.java
    ├──ApiClientInterface.java
├──presenter
    ├──RepoPresenter.java
├──view
    ├──repodetails
        ├──RefreshDataInterface.java
        ├──RepoDetailFragment.java
        ├──RepoDetailsInterface.java
├──AppContract.java
├──MainActivity.java
|
```

The model is a POJO class made with [jsonschema2pojo](http://www.jsonschema2pojo.org)
Most of the fields are **not used in app**, but they are implemented for **future use**.

Networking operations made with **Retrofit**, instantiated in 'ApiClient.java'. Imlemented through 'ApiClientInterface.java' in 'RepoPresenter.java'

The 'AppContract.java' is the **Contract** class and includes the **interfaces** for the **View** and **Presenter** layers. These interfaces contain methods for **fetching** the api and **listing** the response. Interfaces implemented in 'Main Activity.java' and 'RepoPresenter.java'.

Repo detail fragment is called from the click listener of RecyclerView and the parameters are set by an interface cause of the **delay at the view initialization in Fragment**. **Glide** library was used for the avatar loading from the URL.

Starring is stored in **SharedPreferences** and used another interface for refreshing the state of the star items in RecyclerView.

