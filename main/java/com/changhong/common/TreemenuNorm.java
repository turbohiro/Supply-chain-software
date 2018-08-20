package com.changhong.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.changhong.appraise.model.AppraiseNorm;
import com.google.common.collect.Lists;
/**
 * 功能描述：评价指标树
 */

public class TreemenuNorm implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<AppraiseNorm> list;
    private AppraiseNorm root;


	public TreemenuNorm(List<AppraiseNorm> list) {
        this.list = list;
        this.root = new AppraiseNorm();
    }

    /**
     * 组合json
     *
     * @param list
     * @param node
     */
    private Tree getNodeJson(List<AppraiseNorm> list, AppraiseNorm node) {
        Tree tree = new Tree();
        tree.setId("_chmesnorm_" + node.getNormcode());
        tree.setText(node.getNormname());
        tree.setIconCls("file");
        tree.setChildren(new ArrayList<Tree>());
        if (list == null) {
            // 防止没有权限菜单时
            return tree;
        }
        if (hasChild(list, node)) {
            List<Tree> lt = Lists.newArrayList();
            tree.setLeaf(false);//全部为父节点
            tree.setExpanded(true);//默认展开
            List<AppraiseNorm> childList = getChildList(list, node);
            Iterator<AppraiseNorm> it = childList.iterator();
            while (it.hasNext()) {
            	AppraiseNorm modules = it.next();
                // 递归
                lt.add(getNodeJson(list, modules));
            }
            tree.setChildren(lt);
        } else {
            tree.setLeaf(true);//全部为子节点
            tree.setExpanded(true);//默认都不展开
        }
        return tree;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<AppraiseNorm> list, AppraiseNorm node) {
        return getChildList(list, node).size() > 0 ? true : false;
    }

    /**
     * 得到子节点列表
     */
    private List<AppraiseNorm> getChildList(List<AppraiseNorm> list, AppraiseNorm modules) {
        List<AppraiseNorm> li = Lists.newArrayList();
        Iterator<AppraiseNorm> it = list.iterator();
        while (it.hasNext()) {
        	AppraiseNorm temp = it.next();
            if (modules.getNormcode().equals(temp.getNormcodefather())) {
                li.add(temp);
            }
        }
        return li;
    }

    public Tree getTreeJson() {
        // 父机构的id为'1'固定值
        this.root.setNormcode("1");
        return this.getNodeJson(this.list, this.root);
    }

}
