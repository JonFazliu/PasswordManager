package com.example.passwordmanager

class RamMemoryPasswordHolder {
        companion object {
        private var passwords = mutableMapOf(
            "Master Password" to "112332",
            "MASTER_PASSWORD_APP_DONT_CHANGE" to "Master Password"
        )


            fun save(password: Password) {
                passwords.put(password.appName, password.pasword)
            }

            fun edit(id: String, password: Password) {
                passwords.remove(id)
                passwords.put(password.appName, password.pasword)
            }

            fun remove(id: String) {
                passwords.remove(id)
            }

            fun get(): Map<String,String> {
                return passwords.toMap()
            }
        }

        data class Password(val appName: String, val pasword: String)
}