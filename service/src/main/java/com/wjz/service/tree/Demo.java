package com.wjz.service.tree;

public class Demo {
/*	
	@SuppressWarnings("unchecked")
	public List<SysOrganization> selectList() {
		String sql = "SELECT * FROM sys_organization WHERE `status` = '1'";
		return (List<SysOrganization>) getDao().find(sql);
	}

	@Cacheable(value = "Organization", key = "organization:treeify")
	public List<SimpleTreeNode> treeify() {
		List<SimpleTreeNode> ts = null;
		List<SysOrganization> orgs = selectList();
		if (!CollectionUtils.isEmpty(orgs)) {
			ts = treeify(ROOT_ID, orgs);
		}
		return ts;
	}

	private List<SimpleTreeNode> treeify(int pId, List<SysOrganization> orgs) {
		List<SimpleTreeNode> ts = new ArrayList<>();
		for (SysOrganization org : orgs) {
			if (pId != org.getParentId()) {
				continue;
			}
			SimpleTreeNode t;
			ts.add(t = buildTreeNode(org));
			t.setChildren(treeify(org.getId(), orgs));
		}
		return ts;
	}

	private SimpleTreeNode buildTreeNode(SysOrganization org) {
		SimpleTreeNode treeNode = (SimpleTreeNode) tNodeFactory.getTreeNode();
		treeNode.setId(org.getId().toString());
		treeNode.setName(org.getName());
		treeNode.setpId(org.getParentId().toString());
		return treeNode;
	}
*/
}
