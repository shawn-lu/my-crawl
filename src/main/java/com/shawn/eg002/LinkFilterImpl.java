/**
 * Project Name:crawl-app001
 * File Name:LinkFilter.java
 * Package Name:com.shawn.eg002
 * Date:2016-8-13下午3:53:11
 *
 */

package com.shawn.eg002;

import com.shawn.eg001.LinkFilter;

/**
 * ClassName: LinkFilter <br/>
 * Date: 2016-8-13 下午3:53:11 <br/>
 * Description: TODO 
 *
 * @author luxf
 * @version 
 * @see
 */
public class LinkFilterImpl implements LinkFilter{
    private String matchStr = "jsedu.sh.cn";

    public boolean accept(String url) {
        if (url.contains(matchStr)) {
            return true;
        } else {
            return false;
        }
    }

}
