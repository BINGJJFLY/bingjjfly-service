package com.wjz.service.tree;

import java.util.List;

/**
 * <p>
 * 复合类型树形结构
 * </p>
 * <p>
 * 可以是菜单类型等
 * </p>
 * 
 * @author wangjz
 * @date 2020年4月24日
 *
 */
public class MenuTreeNode extends AbstractTreeNode<MenuTreeNode> {

	private static final long serialVersionUID = -5829729963879020387L;

	private String id;
	private String name;
	private String pId;
	private String level;
	private String requestMode;
	private String icon;
	private String url;
	private String path;
	private String openMode;
	private List<MenuTreeNode> children;
	
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRequestMode() {
		return requestMode;
	}
	public void setRequestMode(String requestMode) {
		this.requestMode = requestMode;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOpenMode() {
		return openMode;
	}
	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}
	public List<MenuTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTreeNode> children) {
		this.children = children;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((openMode == null) ? 0 : openMode.hashCode());
		result = prime * result + ((pId == null) ? 0 : pId.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((requestMode == null) ? 0 : requestMode.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		MenuTreeNode other = (MenuTreeNode) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (openMode == null) {
			if (other.openMode != null)
				return false;
		} else if (!openMode.equals(other.openMode))
			return false;
		if (pId == null) {
			if (other.pId != null)
				return false;
		} else if (!pId.equals(other.pId))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (requestMode == null) {
			if (other.requestMode != null)
				return false;
		} else if (!requestMode.equals(other.requestMode))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "MenuTreeNode [id=" + id + ", name=" + name + ", pId=" + pId + ", level=" + level + ", requestMode="
				+ requestMode + ", icon=" + icon + ", url=" + url + ", path=" + path + ", openMode=" + openMode
				+ ", children=" + children + "]";
	}
}
