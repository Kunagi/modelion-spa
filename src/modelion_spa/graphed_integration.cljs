(ns modelion-spa.graphed-integration
 (:require
  [mui-commons.graphed.api :as graphed]))


(defn leaf-node
  [model-db entity-id]
  (let [entity (get model-db entity-id)
        text (:modeling/name entity)]
    {:graphed.node/name text
     :entity entity}))


(defn container-node
  [model-db entity-id childrens-ids]
  (let [entity (get model-db entity-id)
        text (:modeling/name entity)]
    {:graphed.node/name text
     :graphed.node/children childrens-ids
     :entity entity}))


(defn refs
  [model-db entity-id attribute mode]
  (mapv
   (fn [id]
     [mode id])
   (get-in model-db [entity-id attribute])))


(defn refs-node
  [model-db entity-id attribute mode]
  (container-node model-db entity-id (refs model-db entity-id attribute :ref)))


(defn node-for-ref
  [model-db [mode entity-id]]
  (case mode
    :model (refs-node model-db entity-id :model/modules :ref)
    (leaf-node model-db entity-id)))


(defn initial-focused-node-id
  [db]
  [:model "model"])


(defn node-by-id
  [db node-id]
  (let [model-db (get db :modelion/model-db)]
    (node-for-ref model-db node-id)))


(defn init
  []
  (graphed/init
   {:f-initial-focused-node-id initial-focused-node-id
    :f-node-by-id node-by-id}))
