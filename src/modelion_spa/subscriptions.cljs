(ns modelion-spa.subscriptions
  (:require
   [re-frame.core :as rf]
   [facts-db.api :as db]))


(rf/reg-sub
 :modelion/navigator
 (fn [db _]
   (get-in db [:modelion/navigator])))

