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
<ul>
<li>NodeJs ( https://nodejs.org/en/ )</li>
<li>MongoDB ( https://www.mongodb.com/what-is-mongodb ) <br/>
You can use the online Database ( Mongodb Atlas / m-lab) <br/>
For better UI/UX of database you can download ( mongoDB Atlas / Robo3T )</li>
<li>For editor you can use any IDE. (my fav. - Visual Code)</li>
<li>If you want the version control also then install git-bash or you can you Github
desktop</li>
 </ul>
 
 
 ## Day2
   <ul> 
   <li>Setup Your Node-Js Project</li>
   <li>Make a SignUp and Login Routes for the user</li>
   </ul>
      Read this blogs to take help<br>
     https://medium.com/code-to-express/starting-with-nodejs-b70679e8101f<br>https://medium.com/code-to-express/login-and-signup-page-4a65fec162f1


## Day3
So till now a User can SignUp and Login. Now the Next thing to is to make the Question Schema So that a User can Post a Question and also answer to that question. Along with this User can also Upvote the answer. So Please follow these steps:
<ul>
   <li>Make a Question Schema/Model(<i>Take help from Resources</i>)</li>
   <li>Make a route(private route) to post the Question.</li>
   <li>Make a get(public route) route to get all the Questions.</li>
</ul>  

## Day4
So till now a User can Post a Question after they login into the System.So for further Development please follow these steps:
<ul>
   <li>Make a private route to post the Answer only for the existing Questions.</li>
   <li>Make a public route to get all the Answers and it is better to get the complete Question Model.</li>
   <li><i>It is just advice to make the array-String in Model/Schema if you want to have more than one value for same entity. For example we want to save many answers of only one question.</i></li>
</ul>

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

### Question Model

```
const QuestionSchema = new Schema({
  user: {
    type: Schema.Types.ObjectId,
    ref: "myPerson"
  },
  textone: {
    type: String,
    required: true
  },
  texttwo: {
    type: String,
    required: true
  },
  name: {
    type: String
  },
  upvotes: [
    {
      user: {
        type: Schema.Types.ObjectId,
        ref: "myPerson"
      }
    }
  ],
  answers: [
    {
      user: {
        type: Schema.Types.ObjectId,
        ref: "myPerson"
      },
      text: {
        type: String,
        required: true
      },
      name: {
        type: String
      },
      date: {
        type: Date,
        default: Date.now
      }
    }
  ],
  date: {
    type: Date,
    default: Date.now
  }
});

```
