package com.changhong.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.changhong.system.model.Organization;
import com.google.common.collect.Lists;

/**
 * 功能描述：组织机构树
 * @author sw.j
 * @date 2018年5月14日 上午10:30:59
 * @version 1.0
 */
public class TreeMenuOrg implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Organization> list;
    private Organization root;

    public TreeMenuOrg(List<Organization> list) {
        this.list = list;
        this.root = new Organization();
    }

    /**
     * 组合json
     *
     * @param list
     * @param node
     */
    private Tree getNodeJson(List<Organization> list, Organization node) {
        Tree tree = new Tree();
        tree.setId("_chmesorg_" + node.getOrganizationcode());
        tree.setText(node.getOrganizationname());
        tree.setIconCls("file");
        tree.setChildren(new ArrayList<Tree>());
        if (list == null) {
            // 防止没有权限菜单时
            return tree;
        }
        if (hasChild(list, node)) {
            List<Tree> lt = Lists.newArrayList();
            tree.setUrl(node.getPath());
            tree.setLeaf(false);//全部为父节点
            tree.setExpanded(true);//默认展开
            List<Organization> childList = getChildList(list, node);
            Iterator<Organization> it = childList.iterator();
            while (it.hasNext()) {
            	Organization modules = it.next();
                // 递归
                lt.add(getNodeJson(list, modules));
            }
            tree.setChildren(lt);
        } else {
            tree.setUrl(node.getPath());
            tree.setLeaf(true);//全部为子节点
            tree.setExpanded(true);//默认都不展开
        }
        return tree;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Organization> list, Organization node) {
        return getChildList(list, node).size() > 0 ? true : false;
    }

    /**
     * 得到子节点列表
     */
    private List<Organization> getChildList(List<Organization> list, Organization modules) {
        List<Organization> li = Lists.newArrayList();
        Iterator<Organization> it = list.iterator();
        while (it.hasNext()) {
        	Organization temp = it.next();
            if (modules.getOrganizationcode().equals(temp.getFather())) {
                li.add(temp);
            }
        }
        return li;
    }

    public Tree getTreeJson() {
        // 父机构的id为'1'固定值
        this.root.setOrganizationcode("1");
        return this.getNodeJson(this.list, this.root);
    }
}
