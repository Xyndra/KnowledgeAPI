package de.xyndra.knowledgeapi.logic

import kotlinx.serialization.Serializable

@Serializable
// TODO: Find something more efficient than String for pwdHash
sealed class User(val name: String, val email: String?, val pwdHash: String?) {
    data object DeletedUser : User("Deleted User", null, null)
    class UnverifiedUser(name: String, email: String, pwdHash: String) : User(name, email, pwdHash)
    class NormalUser(name: String, email: String, pwdHash: String) : User(name, email, pwdHash)
    class AdminUser(name: String, email: String, pwdHash: String) : User(name, email, pwdHash)
    class DeveloperUser(name: String, email: String, pwdHash: String) : User(name, email, pwdHash)

    fun delete() {
        TODO()
    }
}

typealias UID = Int

// TODO: Hook this up to a DB or smth
val users = mutableMapOf<UID, User>(
    0 to User.DeveloperUser("Xyndra", "sammy@deutschergamingserver.de", "lNgnVzsJEffjtEizpz9trzSSMrKnuFdO2BopNXza9Ao="),
)

fun login(identifier: String, pwd: String): UID? {
    for ((uid, user) in users) {
        if (user.email == identifier || user.name == identifier) {
            if (user.pwdHash == pwd) {
                return uid
            }
        }
    }
    return null
}

fun getUser(uid: UID): User {
    return users[uid] ?: User.DeletedUser
}

fun addUser(user: User): UID {
    val uid = users.size
    users[uid] = user
    return uid
}

fun removeUser(uid: UID) {
    assert(users.containsKey(uid))
    users[uid]!!.delete()
    users[uid] = User.DeletedUser
}