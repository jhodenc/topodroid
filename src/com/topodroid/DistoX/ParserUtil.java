/* @file ParserUtil.java
 *
 * @author marco corvi
 * @date jan 2019
 *
 * @brief TopoDroid parser utils
 *
 * --------------------------------------------------------
 *  Copyright This software is distributed under GPL-3.0 or later
 *  See the file COPYING.
 * ----------------------------------------------------------
 */
package com.topodroid.DistoX;

class ParserUtil
{

  final static int DATA_DEFAULT    = 0;
  final static int DATA_NONE       = DATA_DEFAULT;
  final static int DATA_NORMAL     = 1;
  final static int DATA_TOPOFIL    = 2;
  final static int DATA_CARTESIAN  = 3;
  final static int DATA_CYLPOLAR   = 4;
  final static int DATA_DIVING     = 5;
  final static int DATA_PASSAGE    = 6;
  final static int DATA_DIMENSION  = DATA_PASSAGE;
  final static int DATA_NOSURVEY   = 7;

  final static int CASE_UPPER     = 0;
  final static int CASE_LOWER     = 1;
  final static int CASE_PRESERVE  = 2;


  static String applyCase( int c, String name ) 
  {
    switch ( c ) {
      case CASE_UPPER: return name.toUpperCase();
      case CASE_LOWER: return name.toLowerCase();
      case CASE_PRESERVE:
      default: return name;
    }
  }

  static float parseAngleUnit( String unit )
  {
    String u = unit.toLowerCase();
    // not handled "percent"
    if ( u.startsWith("min") )  return 1/60.0f;
    if ( u.startsWith("grad") ) return (float)TDUtil.GRAD2DEG;
    if ( u.startsWith("mil") )  return (float)TDUtil.GRAD2DEG;
    // if ( unit.startsWith("deg") ) return 1.0f;
    return 1.0f;
  }

  static float parseLengthUnit( String unit )
  {
    String u = unit.toLowerCase();
    if ( u.startsWith("c") ) return 0.01f; // cm centimeter
    if ( u.startsWith("f") ) return (float)TDUtil.FT2M; // ft feet
    if ( u.startsWith("i") ) return (float)TDUtil.IN2M; // in inch
    if ( u.startsWith("milli") || u.equals("mm") ) return 0.001f; // mm millimeter
    if ( u.startsWith("y") ) return (float)TDUtil.YD2M; // yd yard
    // if ( unit.startsWith("m") ) return 1.0f;
    return 1.0f;
  }

  static int parseExtend( String extend, int old_extend )
  {
    // skip: hide, start
    if ( extend.equals("hide") || extend.equals("start") ) {
      return old_extend;
    }
    if ( extend.equals("left") || extend.equals("reverse") ) {
      return DBlock.EXTEND_LEFT;
    } 
    if ( extend.startsWith("vert") ) {
      return DBlock.EXTEND_VERT;
    }
    if ( extend.startsWith("ignore") ) {
      return DBlock.EXTEND_IGNORE;
    }
    // if ( extend.equals("right") || extend.equals("normal") ) {
    //   return DBlock.EXTEND_RIGHT;
    // } 
    return DBlock.EXTEND_RIGHT;
  }

}
