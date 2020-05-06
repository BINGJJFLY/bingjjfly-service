package com.wjz.service.tree;

/**
 * 提供简单树形结构
 * 
 * @author wangjz
 * @date 2020年4月28日
 * @see {@link SimpleTreeNode}
 *
 */
public class SimpleTreeNodeFactory implements TreeNodeFactory {

	@Override
	public TreeNode getTreeNode() {
		return new SimpleTreeNode();
	}

}
