package com.changhong.common;

import com.changhong.system.model.Module;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 菜单类1.1
 *
 * @author sw.j
 * @date 2018-5-10 下午9:53:59
 */
public class TreeMenu implements Serializable {
    private static final long serialVersionUID = -1837699104579478927L;
    private List<Module> list;
    private Module root;

    public TreeMenu(List<Module> list) {
        this.list = list;
        this.root = new Module();
    }

    /**
     * 组合json
     *
     * @param list
     * @param node
     */
    private Tree getNodeJson(List<Module> list, Module node) {
        Tree tree = new Tree();
        tree.setId("_chmesmodules_" + node.getId());
        tree.setText(node.getModelname());
        tree.setIconCls(node.getDllfname());
        tree.setChildren(new ArrayList<Tree>());
        if (list == null) {
            // 防止没有权限菜单时
            return tree;
        }
        if (hasChild(list, node)) {
            List<Tree> lt = Lists.newArrayList();
            tree.setUrl("");
            tree.setLeaf(node.getTransflag().equals("1") ? true : false);
            tree.setExpanded(node.getIsshow());
            List<Module> childList = getChildList(list, node);
            Iterator<Module> it = childList.iterator();
            while (it.hasNext()) {
            	Module modules = it.next();
                // 递归
                lt.add(getNodeJson(list, modules));
            }
            tree.setChildren(lt);
        } else {
            tree.setUrl(node.getClassname());
            tree.setLeaf(node.getTransflag().equals("1") ? true : false);
            tree.setExpanded(node.getIsshow());
        }
        return tree;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Module> list, Module node) {
        return getChildList(list, node).size() > 0 ? true : false;
    }

    /**
     * 得到子节点列表
     */
    private List<Module> getChildList(List<Module> list, Module modules) {
        List<Module> li = Lists.newArrayList();
        Iterator<Module> it = list.iterator();
        while (it.hasNext()) {
        	Module temp = it.next();
            if (modules.getId().equals(temp.getParentid())) {
                li.add(temp);
            }
        }
        return li;
    }

    public Tree getTreeJson() {
        // 父菜单的id为'C0C50F39-DAF8-4696-B611-3084FAE1EFEC'固定值
        this.root.setId("C0C50F39-DAF8-4696-B611-3084FAE1EFEC");
        this.root.setTransflag("0");
        this.root.setIsshow(true);
        return this.getNodeJson(this.list, this.root);
    }
}
