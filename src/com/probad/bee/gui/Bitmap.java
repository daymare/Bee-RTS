package com.probad.bee.gui;

//********************************************************\\
//* Name: Bitmap                                         *\\
//* Desc: Graphics data storage. Uses ARGB Integers for  *\\
//*       pixel data. A simple image file. If you can    *\\
//*       understand these methods then feel free to     *\\
//*       write your own bitmap methods.                 *\\
//********************************************************\\


import com.probad.bee.*;

public class Bitmap {
 
 public final int width;
 public final int height;
 public final int[] pixels;
 
 public Bitmap(int width, int height) {
    this.width = width;
    this.height = height;
    pixels = new int[width * height];
 }
 
 // Draws the Bitmap at the x and y coordinate.
 public void draw(Bitmap bitmap, int xOffs, int yOffs) {
    for (int y = 0; y < bitmap.height; y++) {
       int yPix = y + yOffs;
       if (yPix < 0 || yPix >= height) 
          continue;
    
       for (int x = 0; x < bitmap.width; x++) {
          int xPix = x + xOffs;
          if (xPix < 0 || xPix >= width) 
             continue;
       
          int src = bitmap.pixels[x + y * bitmap.width];
          if(src != 0) {
          	pixels[xPix + yPix * width] = src;
          }
       }
    }
 }
 
 // Draws the Bitmap at the x and y coordinate flipped.
 public void flipDraw(Bitmap bitmap, int xOffs, int yOffs) {
    for (int y = 0; y < bitmap.height; y++) {
       int yPix = y + yOffs;
       if (yPix < 0 || yPix >= height) 
          continue;
    
       for (int x = 0; x < bitmap.width; x++) {
          int xPix = xOffs + bitmap.width - x - 1;
          if (xPix < 0 || xPix >= width) 
             continue;
       
          int src = bitmap.pixels[x + y * bitmap.width];
          if(src != 0) {
          	pixels[xPix + yPix * width] = src;
          }
       }
    }
 }
 
 // Draws the Bitmap at the x and y coordinate with additional options, broken.
 public void draw(Bitmap bitmap, int xOffs, int yOffs, int xo, int yo, int w, int h, int col) {
    for (int y = 0; y < h; y++) {
       int yPix = y + yOffs;
       if (yPix < 0 || yPix >= height) 
          continue;
    
       for (int x = 0; x < w; x++) {
          int xPix = x + xOffs;
          if (xPix < 0 || xPix >= width) 
             continue;
       
          int src = bitmap.pixels[(x + xo) + (y + yo) * bitmap.width];
          if (src >= 0) {
             pixels[xPix + yPix * width] = src * col;
          }
       }
    }
 }
 
 // Draws the Bitmap at the x and y coordinate resized by scale with additional options, broken
 public void scaleDraw(Bitmap bitmap, int scale, int xOffs, int yOffs, int xo, int yo, int w, int h, int col) {
    for (int y = 0; y < h * scale; y++) {
       int yPix = y + yOffs;
       if (yPix < 0 || yPix >= height) 
          continue;
    
       for (int x = 0; x < w * scale; x++) {
          int xPix = x + xOffs;
          if (xPix < 0 || xPix >= width) 
             continue;
       
          int src = bitmap.pixels[(x / scale + xo) + (y / scale + yo) * bitmap.width];
          if (src >= 0) {
             pixels[xPix + yPix * width] = src * col;
          }
       }
    }
 }
 
 // Fills the rectangle between x0, y0 and x1, y1 with color.
 public void fill(int x0, int y0, int x1, int y1, int color) {
    for (int y = y0; y < y1; y++) {
       for (int x = x0; x < x1; x++) {
          pixels[x + y * width] = color;
       }
    }
 }
}