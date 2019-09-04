(ns modelion-spa.subscriptions
  (:require
   [re-frame.core :as rf]
   [facts-db.api :as db]))


(rf/reg-sub
 :modelion/editor-db
 (fn [db _]
   (get-in db [:modelion/editor-db])))
