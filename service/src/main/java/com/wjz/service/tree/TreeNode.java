package com.wjz.service.tree;

import java.io.Serializable;

/**
 * 树形结构
 * 
 * @author wangjz
 * @date 2020年4月24日
 *
 */
public interface TreeNode extends Serializable {

	/**
	 * 是否有子节点
	 * 
	 * @return
	 */
	default boolean isHasChildren() {
		return false;
	}

}
