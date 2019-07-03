# About The Project
This is the Project of Minature StackOverflow API which is an API's collection which can be used to make the Backend of any Fullstack Application. These are the folllowing features in the given API's
<ul>
<li>Create a User</li>
<li>Post a Question</li>
<li>Post a Answer to the existing question</li>
<li>Clap on the answer</li>
<li>Auhtentication and Role based Access to the StackHolders</li>
</ul>

# Tasks to be Done
## Day1
   Start with the installation
<ol>
<li>NodeJs ( https://nodejs.org/en/ )</li>
<li>MongoDB ( https://www.mongodb.com/what-is-mongodb ) <br/>
You can use the online Database ( Mongodb Atlas / m-lab) <br/>
For better UI/UX of database you can download ( mongoDB Atlas / Robo3T )</li>
<li>For editor you can use any IDE. (my fav. - Visual Code)</li>
<li>If you want the version control also then install git-bash or you can you Github
desktop</li>
 </ol>
 
 
 ## Day2
   <ol> 
   <li>Setup Your Node-Js Project</li>
   <li>Make a SignUp and Login Routes for the user</li>
   </ol>
      Read this blogs to take help<br>
     https://medium.com/code-to-express/starting-with-nodejs-b70679e8101f<br>https://medium.com/code-to-express/login-and-signup-page-4a65fec162f1


# Resources 
For the help Go into this repo Star and fork 
https://github.com/HrithikMittal/Minature-Stackoverflow-APIs/

# Guidance

### Person Model(User)

```
const PersonSchema = new Schema({
  name: {
    type: String,
    required: true
  },
  email: {
    type: String,
    required: true
  },
  password: {
    type: String,
    required: true
  },
  username: {
    type: String
  },
  profilepic: {
    type: String,
    default: "https://learncodeonline.in/manicon.png"
  },
  date: {
    type: Date,
    default: Date.now
  }
});
```

### Profile Model(User's Profile)
```
const ProfileSchema = new Schema({
  user: {
    type: Schema.Types.ObjectId,
    ref: "myPerson"
  },
  username: {
    type: String,
    required: true,
    max: 50
  },
  website: {
    type: String
  },
  country: {
    type: String
  },
  languages: {
    type: [String],
    required: true
  },
  portfolio: {
    type: String
  },
  workrole: [
    {
      role: {
        type: String,
        required: true
      },
      company: {
        type: String
      },
      country: {
        type: String
      },
      from: {
        type: Date
      },
      to: {
        type: Date
      },
      current: {
        type: Boolean,
        default: false
      },
      details: {
        type: String
      }
    }
  ],
  social: {
    youtube: {
      type: String
    },
    facebook: {
      type: String
    },
    instagram: {
      type: String
    }
  },
  date: {
    type: Date,
    default: Date.now
  }
});
```
