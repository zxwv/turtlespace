package net.zxwv.turtlespace.test.util

import net.zxwv.turtlespace.test.BaseSpec

import scala.collection.mutable.Set

import net.zxwv.turtlespace.util.WithPreAndPost.PreAndPostWrapper

class WithPreAndPostSpec extends BaseSpec {
  
  behavior of "WithPreAndPost"
  
  "#withPreAndPost" should "behave correctly when no exception is thrown" in {
    val set = Set(1)
    set.withPreAndPost({ s =>
      s += 2
    }, { s =>
      s += 3
    }, { s =>
      s += 4
    })
    set should equal (Set(1, 2, 3, 4))
  }
  
  "#withPreAndPost" should "behave correctly when an exception is thrown" in {
    val set = Set(1)
    the [Exception] thrownBy {
      set.withPreAndPost({ s =>
        s += 2
      }, { s =>
        s += 3
        throw new Exception("foo")
      }, { s =>
        s += 4
      })
    } should have message "foo"
    set should equal (Set(1, 2, 3, 4))
  }
  
}