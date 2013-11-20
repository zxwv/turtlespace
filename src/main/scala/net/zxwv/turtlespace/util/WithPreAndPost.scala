package net.zxwv.turtlespace.util

object WithPreAndPost {
  
  implicit class Wrapper[T](content: T) {
    
    def withPreAndPost[R](pre: T => Unit, action: T => R, post: T => Unit): R = {
      pre(content)
      try {
        action(content)
      } finally {
        post(content)
      }
    }
    
  }
  
}