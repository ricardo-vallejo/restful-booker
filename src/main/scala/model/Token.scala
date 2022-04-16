package model

/**
 * Token Case Class
 * @param username Username for authentication
 * @param password Password for authentication
 */
case class Token(username: String = "admin", password: String = "password123")