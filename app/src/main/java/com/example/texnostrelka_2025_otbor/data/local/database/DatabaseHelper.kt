package com.example.texnostrelka_2025_otbor.data.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Comics.db"
        private const val DATABASE_VERSION = 9

        private const val CREATE_TABLE_COMICS = """
            CREATE TABLE comics (
                id TEXT PRIMARY KEY,
                text TEXT,
                description TEXT
            )
        """

        private const val CREATE_TABLE_PAGES = """
            CREATE TABLE pages (
                pageId TEXT PRIMARY KEY,
                comicsId TEXT,
                number INTEGER NOT NULL,
                rows INTEGER NOT NULL,
                columns INTEGER NOT NULL,
                FOREIGN KEY(comicsId) REFERENCES comics(id) ON DELETE CASCADE
            )
        """

        private const val CREATE_TABLE_IMAGE = """
            CREATE TABLE image (
                id TEXT PRIMARY KEY,
                pageId TEXT,
                cellIndex INTEGER,
                image BLOB NOT NULL,
                FOREIGN KEY(pageId) REFERENCES pages(pageId) ON DELETE CASCADE
            )
        """
    }

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_COMICS)
        db.execSQL(CREATE_TABLE_PAGES)
        db.execSQL(CREATE_TABLE_IMAGE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS image")
        db.execSQL("DROP TABLE IF EXISTS pages")
        db.execSQL("DROP TABLE IF EXISTS comics")
        onCreate(db)
    }
}
