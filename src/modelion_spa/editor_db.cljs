(ns modelion-spa.editor-db
  (:require
   [facts-db.api :as db]
   [facts-db.ddapi :refer [def-api def-event] :as ddapi]))


(def root-id "modelion/editor")


(defn new-editor-db
  []
  (-> (ddapi/new-db :modelion.editor-db {})
      (ddapi/events>
       [
        [:node-appended
         {:node {:db/id "n-type-only"
                 :node/type :type-only}
          :parent-node-id "root-node"}]

        [:node-appended
         {:node {:db/id "n-text-only"
                 :node/text "text only"}
          :parent-node-id "root-node"}]

        [:node-appended
         {:node {:db/id "n-children-only"
                 :node/children []}
          :parent-node-id "root-node"}]

        [:node-appended
         {:node {:db/id "n-string"
                 :node/type :string
                 :node/text "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."}
          :parent-node-id "root-node"}]

        [:node-appended
         {:node {:db/id "n-vector"
                 :node/type :vector
                 :node/children []}
          :parent-node-id "root-node"}]

        [:node-appended
         {:node {:db/id "n-a"
                 :node/type :keyword
                 :node/text ":a"}
          :parent-node-id "n-vector"}]

        [:node-appended
         {:node {:db/id "n-b"
                 :node/type :keyword
                 :node/text ":b"}
          :parent-node-id "n-vector"}]

        [:node-appended
         {:node {:db/id "n-c"
                 :node/type :keyword
                 :node/text ":c"}
          :parent-node-id "n-vector"}]])))


(def-api ::modelion.editor-db
  :db-constructor
  (fn [_]
    [{:db/id root-id
      :editor/active-lense "dummy-lense"}
     {:db/id "dummy-lense"
      :lense/label "Dummy Lense"
      :lense/root-node "root-node"}
     {:db/id "root-node"
      :node/type :root
      :node/text "Lense Root Node"
      :node/children []}]))


(def-event ::node-appended
  (fn [db {:keys [node parent-node-id]}]
    [node]
    [(merge node
            {:node/parent parent-node-id
             :db/add-ref-n [parent-node-id :node/children]})
     {:db/id root-id
      :editor/focused-node (-> node :db/id)}]))
