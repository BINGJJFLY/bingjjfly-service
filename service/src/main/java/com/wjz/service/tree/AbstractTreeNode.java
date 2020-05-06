package com.wjz.service.tree;

import java.util.List;

/**
 * 树形结构对象的抽象
 * 
 * @author wangjz
 * @date 2020年4月28日
 *
 * @param <T>
 */
public abstract class AbstractTreeNode<T> implements TreeNode {

	private static final long serialVersionUID = 1L;

	protected abstract List<T> getChildren();

	@Override
	public boolean isHasChildren() {
		return getChildren() != null && !getChildren().isEmpty();
	}
}
