package net.zxwv.turtlespace.test.graphics

import net.zxwv.turtlespace.test.BaseSpec
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import net.zxwv.turtlespace.graphics.Graphics2DWrapper.Graphics2DWrapperImpl
import java.awt.geom.AffineTransform

class Graphics2DWrapperSpec extends BaseSpec {
  
  var bufferedImage: BufferedImage = null
  var graphics2d: Graphics2D = null
  
  behavior of "Graphics2DWrapper"
  
  before {
    bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB)
    graphics2d = bufferedImage.getGraphics.asInstanceOf[Graphics2D]
  }
  
  "#withTransform" should
    "properly apply and restore the transformation when no exception is thrown" in {
    val firstTransform = graphics2d.getTransform
    val addTransform = AffineTransform.getTranslateInstance(1.25, 3.14)
    var secondTransform: AffineTransform = null
    graphics2d.withTransform(addTransform, { g2d =>
      secondTransform = g2d.getTransform
    })
    val thirdTransform = graphics2d.getTransform
    firstTransform should equal (thirdTransform)
    val expectedSecondTransform = firstTransform.clone.asInstanceOf[AffineTransform]
    expectedSecondTransform.translate(1.25, 3.14)
    secondTransform should equal (expectedSecondTransform)
    firstTransform should not equal (secondTransform)
    secondTransform should not equal (thirdTransform)
  }
  
  it should
    "properly apply and restore the transformation when exception is thrown" in {
    val firstTransform = graphics2d.getTransform
    val addTransform = AffineTransform.getTranslateInstance(1.25, 3.14)
    var secondTransform: AffineTransform = null
    the [Exception] thrownBy {
      graphics2d.withTransform(addTransform, { g2d =>
        secondTransform = g2d.getTransform
        throw new Exception("foo")
      })
    } should have message "foo"
    val thirdTransform = graphics2d.getTransform
    firstTransform should equal (thirdTransform)
    val expectedSecondTransform = firstTransform.clone.asInstanceOf[AffineTransform]
    expectedSecondTransform.translate(1.25, 3.14)
    secondTransform should equal (expectedSecondTransform)
    firstTransform should not equal (secondTransform)
    secondTransform should not equal (thirdTransform)
  }
  
}