package dmitriy.losev.core.core

import org.koin.core.qualifier.named

val fileQualifier = named(name = "crypto_datastore_file")

val masterKeyQualifier = named(name = "crypto_datastore_master_key")

val cryptoDataStore = named(name = "crypto_datastore")

val dataStore = named(name = "datastore")