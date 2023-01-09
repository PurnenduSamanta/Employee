package com.purnendu.employee.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EmployeeModel::class], version = 1, exportSchema = false)
abstract class EmployeeDb : RoomDatabase() {

    abstract fun EmployeeDao(): EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE: EmployeeDb? = null

        fun getDataBase(context: Context): EmployeeDb {
            if (INSTANCE == null) {
                synchronized(this)
                {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                       EmployeeDb::class.java,
                        "EmployeeDb"
                    ).build()
                }
            }
            return INSTANCE!!

        }
    }

}