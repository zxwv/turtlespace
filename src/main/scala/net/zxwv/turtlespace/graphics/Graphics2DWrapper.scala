package net.zxwv.turtlespace.graphics

import java.awt.Graphics2D
import java.awt.geom.AffineTransform

import net.zxwv.turtlespace.util.WithPreAndPost.PreAndPostWrapper

object Graphics2DWrapper {
  
  implicit class Graphics2DWrapperImpl(graphics2d: Graphics2D) {
    
    def withTransform[T](transform: AffineTransform, action: Graphics2D => T): T = {
      var oldTransform: AffineTransform = null;
      graphics2d.withPreAndPost({ g2d =>
        oldTransform = g2d.getTransform
        g2d.transform(transform)
      }, { g2d =>
        action(g2d)
      }, { g2d =>
        g2d.setTransform(oldTransform)
      })
    }
    
  }
  
}