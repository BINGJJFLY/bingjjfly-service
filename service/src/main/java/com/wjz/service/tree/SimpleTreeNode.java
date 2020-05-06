package com.wjz.service.tree;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * <p>
 * 简单类型树形结构
 * </p>
 * <p>
 * 仅包含基本属性
 * </p>
 * 
 * @author wangjz
 * @date 2020年4月24日
 *
 */
public class SimpleTreeNode extends AbstractTreeNode<SimpleTreeNode> {

	private static final long serialVersionUID = 3562716065807810453L;

	private String id;
	private String name;
	private String pId;
	private List<SimpleTreeNode> children;

	@Override
	public boolean isHasChildren() {
		return !CollectionUtils.isEmpty(children);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public List<SimpleTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<SimpleTreeNode> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pId == null) ? 0 : pId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleTreeNode other = (SimpleTreeNode) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pId == null) {
			if (other.pId != null)
				return false;
		} else if (!pId.equals(other.pId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SimpleTreeNode [id=" + id + ", name=" + name + ", pId=" + pId + ", children=" + children + "]";
	}
}
