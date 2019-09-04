(ns modelion-spa.ui-components.editor
  (:require
   ["@material-ui/core" :as mui]
   [re-frame.core :as rf]

   [facts-db.api :as db]
   [mui-commons.components :as muic]))


(declare Node)


(defn NodeContent [node]
  [:div
   [:div
    [:span
     {:style {:font-family :monospace}}
     (str (-> node :node/type))]
    " "
    (-> node :node/text)]
   (into
    [:div
     {:style {:display :flex
              :flex-direction :column
              :flex-wrap :wrap
              :margin-left "-5px"
              :margin-bottom "-5px"}}]
    (map
     (fn [child]
       [:div
        {:style {:margin "5px"}}
        [Node child]])
     (-> node :node/children)))])


(defn NodeFrame [node focused?]
  ;; TODO selected?
  [:div
   [:> mui/Paper
    {:style {:padding "10px"
             :background-color (if focused? "#eee")}}
    ;; "node"
    ;; [:pre (str node)]
    ;; [:hr]
    [:div
     [NodeContent node]]]])


(defn Node [node-id]
  (let [editor-db @(rf/subscribe [:modelion/editor-db])
        editor (db/tree editor-db "modelion/editor" {})
        node (db/tree editor-db node-id {})
        focused? (= (-> node :db/id) (-> editor :editor/focused-node))]
    [NodeFrame node focused?]))







(defn EditorLense [editor-db lense-id]
  (let [lense (db/tree editor-db lense-id {})]
    [:div
     {:tab-index 123
      :on-key-press (fn [e] (rf/dispatch [:modelion/editor-key-pressed (.-key e)]))}
     "Lense: " (-> lense :lense/label)
     [:hr]
     [:pre (str "lense: " lense)]
     [:hr]
     [Node (-> lense :lense/root-node)]]))


(defn Editor []
  (let [editor-db @(rf/subscribe [:modelion/editor-db])]
    [EditorLense editor-db "dummy-lense"]))
